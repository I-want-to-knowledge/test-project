package com.geo.source.redis.delaytask_v2;

import com.alibaba.fastjson.JSONObject;
import com.geo.source.redis.delaytask_v2.timer.DelayTaskConstant;
import com.geo.source.redis.delaytask_v2.timer.DelayTaskDto;
import com.geo.source.redis.delaytask_v2.timer.DelayTaskTimer;
import redis.clients.jedis.JedisCluster;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;

/**
 * 延迟任务-生产者
 * 在使用的中心，把该类放进spring的容器内，就可以生产延迟任务了
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
     * 不允许无参初始化
     */
    private DelayTaskProducer() {
    }

    /**
     * 延迟任务生产者
     *
     * @param redis    redis 初始化
     * @param redisKey 不同中心指定不同的key值，否则会不消费
     */
    public DelayTaskProducer(JedisCluster redis, String redisKey) {
        if (redisKey == null || redisKey.isEmpty()) {
            throw new IllegalArgumentException("初始化延迟任务生产者，redis key必须输入！");
        }

        redisKey = DelayTaskConstant.REDIS_KEY + redisKey;
        this.redis = redis;
        this.redisKey = redisKey;
        // 延迟任务计时器启动
        new DelayTaskTimer(redis, redisKey).afterPropertiesSet();
    }

    /**
     * 生产任务
     * 注意：通过是实现DelayTaskConsumer接口，进行消费
     *
     * @param clazz  消费者类
     * @param params 传参
     * @param time   任务延迟消费的时间（单位：秒）
     * @return 是否生产
     */
    public boolean producer(Class<? extends DelayTaskConsumer> clazz, Map<String, Object> params, long time) {
        // 添加消息队列
        final Long aLong = redis.zadd(redisKey, LocalDateTime.now().plusSeconds(time).toEpochSecond(ZoneOffset.of("+8")),
                JSONObject.toJSONString(new DelayTaskDto(clazz, params)));
        return aLong != null && aLong > 0;
    }
}
