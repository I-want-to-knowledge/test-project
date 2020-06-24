package com.geo.source.redis.delaytask_v2.timer;

import com.alibaba.fastjson.JSONObject;
import com.geo.source.redis.delaytask_v2.DelayTaskConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisCluster;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 延迟任务计时器
 *
 * @author YanZhen
 * @date 2020/04/24 13:34
 **/
public class DelayTaskTimer {
    private Logger logger = LoggerFactory.getLogger(DelayTaskTimer.class);
    private ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(1, r -> {
        Thread t = new Thread(r, "Delay_Task_Timer-thread-" + r.hashCode());
        logger.info("{} has been created", t.getName());
        return t;
    });

    /**
     * redis初始化值
     */
    private JedisCluster jedisClusterCache;

    /**
     * 获取服务初始化的redis key
     */
    private String redisKey;

    /**
     * 初始化延迟任务计时器
     *
     * @param jedisClusterCache redis
     * @param redisKey          key
     */
    public DelayTaskTimer(JedisCluster jedisClusterCache, String redisKey) {
        this.jedisClusterCache = jedisClusterCache;
        this.redisKey = redisKey;
    }

    /**
     * 启动延迟任务计时器
     */
    public void afterPropertiesSet() {
        logger.info("延迟任务计时器已启动");
        poolExecutor.execute(this::timer);
    }

    /**
     * 计时器
     */
    private void timer() {
        while (true) {
            try {
                // 业务处理
                logicalProces();

                // 正常休眠1秒
                Thread.sleep(1000);
            } catch (Exception e) {
                // 异常的情况
                logger.error("逻辑处理时，延迟任务计时器异常停止！", e);
                return;
            }
        }
    }

    /**
     * 逻辑处理
     */
    private void logicalProces() {
        // 查询redis队列中有无要执行的任务
        final Set<String> set = jedisClusterCache.zrangeByScore(redisKey, 0, LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")));
        if (set == null || set.isEmpty()) {
            return;
        }

        // 有多个任务的情况
        for (String v : set) {
            poolExecutor.execute(() -> {
                // 解析入参和目标消费者
                final DelayTaskDto dto;
                final DelayTaskConsumer consumer;
                try {
                    dto = JSONObject.parseObject(v, DelayTaskDto.class);
                    consumer = dto.getClazz().newInstance();
                } catch (Exception e) {
                    logger.error("延迟定时器查寻redis的结果（{}）解析失败，可能原因：类未找到！", v, e);
                    return;
                }
                // 移除任务
                final Long zrem = jedisClusterCache.zrem(redisKey, v);
                if (zrem != null && zrem > 0) {
                    try {
                        // 消费任务
                        consumer.consumer(dto.getParams());
                    } catch (Exception e) {
                        logger.error("参数为（{}），初始化消费者时出现异常，延迟任务执行失败！", v, e);
                    }
                } else {
                    logger.error("参数为（{}），Redis中该值已经不存在，重复删除！", v);
                }
            });
        }
    }
}
