package com.geo.source.redis.delaytask_v2.timer;

import com.alibaba.fastjson.JSONObject;
import com.geo.source.redis.delaytask_v2.DelayTaskConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import redis.clients.jedis.JedisCluster;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 延迟任务计时器
 *
 * @author YanZhen
 * @date 2020/04/24 13:34
 **/
public class DelayTaskTimer {
    private final Logger logger = LoggerFactory.getLogger(DelayTaskTimer.class);

    /**
     * 延迟任务线程池
     */
    private final ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(8, r -> {
        Thread t = new Thread(r, "Delay_Task_Timer-thread-" + r.hashCode());
        logger.info("{} has been created", t.getName());
        return t;
    });

    /**
     * redis初始化值
     */
    private final JedisCluster jedisClusterCache;

    /**
     * 获取服务初始化的redis key
     */
    private final String redisKey;

    /**
     * spring上下文
     */
    private final ApplicationContext context;

    /**
     * 异常时，默认不终止计时器
     */
    private boolean stopTimer = false;

    /**
     * 初始化延迟任务计时器
     *
     * @param context           spring上下文
     * @param jedisClusterCache redis
     * @param redisKey          key
     */
    public DelayTaskTimer(ApplicationContext context, JedisCluster jedisClusterCache, String redisKey) {
        this.context = context;
        this.jedisClusterCache = jedisClusterCache;
        this.redisKey = redisKey;

        afterPropertiesSet();
    }

    /**
     * 启动延迟任务计时器
     */
    private void afterPropertiesSet() {
        // 等待5分钟，无任务线程终止
        poolExecutor.setKeepAliveTime(5, TimeUnit.MINUTES);
        poolExecutor.allowCoreThreadTimeOut(true);
        poolExecutor.scheduleWithFixedDelay(timer(), 10, 1, TimeUnit.SECONDS);
        logger.info("延迟任务计时器已启动");
    }

    /**
     * 计时器
     */
    private Runnable timer() {
        return () -> {
            try {
                // 业务处理
                process();

                // 无任务时休息十分钟，或者有任务放入
                waitSleep();
            } catch (Exception e) {
                // 异常的情况
                logger.error("逻辑处理时，延迟任务计时器异常停止！", e);

                // 异常时，计时器是否终止
                if (stopTimer) {
                    return;
                }
                sysSleep();
            }
        };
    }

    /**
     * 当意外终止，系统休息30分钟
     */
    private void sysSleep() {
        try {
            Thread.sleep(DelayTaskConstant.SYSTEM_ERROR_SLEEP);
        } catch (InterruptedException ex) {
            logger.error("sleep异常！", ex);
        }
    }

    /**
     * 无任务时休息十分钟，或者有任务放入则结束休息
     *
     * @throws InterruptedException 非法调用异常
     */
    private void waitSleep() throws InterruptedException {
        Long zCount = jedisClusterCache.zcard(redisKey);
        if (zCount == null || zCount < 1) {
            synchronized (this) {
                this.wait(DelayTaskConstant.SYSTEM_NOTHING_SLEEP);
            }
        }
    }

    /**
     * 解除睡眠，开始运行
     */
    public void notifyAll1() {
        poolExecutor.execute(() -> {
            synchronized (this) {
                this.notifyAll();
            }
        });
    }

    /**
     * 逻辑处理
     */
    private void process() {
        // 查询redis队列中有无要执行的任务
        final Set<String> set = jedisClusterCache.zrangeByScore(redisKey, 0, LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")));
        if (set == null || set.isEmpty()) {
            return;
        }

        // 有多个任务的情况，循环放入线程池进行处理
        for (String v : set) {
            poolExecutor.execute(() -> processTask(v));
        }
    }

    /**
     * 处理任务
     *
     * @param v redis value
     */
    private void processTask(String v) {
        // 解析入参和目标消费者
        final DelayTaskDto dto;
        final DelayTaskConsumer consumer;
        try {
            // 解析传递的实体
            dto = JSONObject.parseObject(v, DelayTaskDto.class);
            // 初始化消费者，需要注入时通过构造函数初始化
            consumer = getDelayTaskConsumer(dto);
        } catch (Exception e) {
            logger.error("延迟定时器查寻redis的结果（{}）解析失败，可能原因：类未找到！", v, e);
            // 解析失败，从redis中删除
            jedisClusterCache.zrem(redisKey, v);
            logger.warn("延迟任务为（key : {}，value : {}）解析失败，该延迟任务删除！", redisKey, v);
            return;
        }

        // 移除任务，移除成功后消费任务，否则不消费
        final Long zrem = jedisClusterCache.zrem(redisKey, v);
        if (zrem == null || zrem < 1) {
            logger.error("参数为（{}），Redis中该值已经不存在，重复删除！", v);
            return;
        }

        try {
            // 消费任务
            consumer.consumer(dto.getParams());
        } catch (Exception e) {
            logger.error("参数为（{}），初始化消费者时出现异常，延迟任务执行失败！", v, e);
        }
    }

    /**
     * 初始化消费者，需要注入时通过构造函数初始化
     *
     * @param dto 延时任务传递实体
     * @return 初始化的消费者
     */
    private DelayTaskConsumer getDelayTaskConsumer(DelayTaskDto dto) {
        // 根据消费者实现类名，从spring容器中获取
        return context.getBean(dto.getClazz());
    }

    /**
     * 异常时是否终止计时器，默认不终止
     *
     * @param flag 是否终止，true是
     */
    public void setFlag(boolean flag) {
        this.stopTimer = flag;
    }

//    /**
//     * 首字母小写
//     *
//     * @param string 字符串
//     * @return 首字母小写
//     */
//    private String firstLowerCase(String string) {
//        if (string == null) {
//            return null;
//        }
//        char[] ch = string.toCharArray();
//        if (ch[0] >= 'A' && ch[0] <= 'Z') {
//            ch[0] += 32;
//        }
//        return String.valueOf(ch);
//    }
}
