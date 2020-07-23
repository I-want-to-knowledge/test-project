package com.geo.source.redis.delaytask_v2;

import com.alibaba.fastjson.JSONObject;
import com.geo.source.redis.delaytask_v2.timer.DelayTaskConstant;
import com.geo.source.redis.delaytask_v2.timer.DelayTaskDto;
import com.geo.source.redis.delaytask_v2.timer.DelayTaskTimer;
import org.springframework.context.ApplicationContext;
import redis.clients.jedis.JedisCluster;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 延迟任务-生产者
 * <pre>
 * 消费任务：实现DelayTaskConsumer接口，并注入spring容器中（例：使用@Service）；
 * 如何生产任务：通过注入该类，使用producer方法生产延迟任务
 * </pre>
 *
 * @author YanZhen
 * @date 2020/04/16 00:19
 **/
public class DelayTaskProducer {

    /**
     * redis初始化
     */
    private JedisCluster redis;
    /**
     * redis key 不同中心指定不同的key值
     */
    private String redisKey;

    /**
     * 定时器对象
     */
    private DelayTaskTimer timer;

    /**
     * 不允许无参初始化
     */
    private DelayTaskProducer() {
    }

    /**
     * 延迟任务生产者
     *
     * @param context  spring上下文
     * @param redis    redis 初始化
     * @param redisKey 不同中心指定不同的key值，否则会不消费
     */
    public DelayTaskProducer(ApplicationContext context, JedisCluster redis, String redisKey) {
        if (redisKey == null || redisKey.isEmpty()) {
            throw new IllegalArgumentException("初始化延迟任务生产者，redis key必须输入！");
        }

        redisKey = DelayTaskConstant.REDIS_KEY + redisKey;
        this.redis = redis;
        this.redisKey = redisKey;
        // 延迟任务计时器启动
        this.timer = new DelayTaskTimer(context, redis, redisKey);
    }

    /**
     * 生产任务
     * 注意：通过是实现DelayTaskConsumer接口，进行消费
     *
     * @param clazz  自定义的消费者类实现类
     * @param params 传参
     * @param time   任务延迟消费的时间（单位：秒）
     * @return 是否生产
     */
    public boolean producer(Class<? extends DelayTaskConsumer> clazz, String params, long time) {
        // 添加消息队列
        final Long aLong = redis.zadd(redisKey, LocalDateTime.now().plusSeconds(time).toEpochSecond(ZoneOffset.of("+8")),
                JSONObject.toJSONString(new DelayTaskDto(clazz, params)));

        boolean b = aLong != null && aLong > 0;
        if (b) {
            // 解除定时器的睡眠
            timer.notifyAll1();
        }
        return b;
    }

    /**
     * 取消延迟任务
     * @param clazz 自定义的消费者实现类
     * @param params 传参；注意：一定要和生成任务时传递的参数相同，否则无法定位redis里的任务
     * @return 是否成功
     */
    public boolean cancel(Class<? extends DelayTaskConsumer> clazz, String params) {
        // 移除任务，移除成功后消费任务，否则不消费
        final Long count = redis.zrem(redisKey, JSONObject.toJSONString(new DelayTaskDto(clazz, params)));
        return count != null && count > 0;
    }
}
