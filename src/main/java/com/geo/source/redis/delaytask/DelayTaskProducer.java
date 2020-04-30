package com.geo.source.redis.delaytask;

import com.alibaba.fastjson.JSONObject;
import com.geo.source.redis.delaytask.constant.Constant;
import com.geo.source.redis.delaytask.dto.DelayTaskDto;
import redis.clients.jedis.JedisCluster;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;

/**
 * 延迟任务-生产
 * @author YanZhen
 * @date 2020/04/16 00:19
 **/
public class DelayTaskProducer {

    private JedisCluster redis;

    public DelayTaskProducer(JedisCluster redis) {
        this.redis = redis;
    }

    /**
     * 生产任务
     * @param clazz 消费者类
     * @param params 传参
     * @param runTime 任务消费的时间
     * @return 是否生产
     */
    public boolean producer(Class<? extends DelayTaskConsumer> clazz, Map<String, Object> params, LocalDateTime runTime) {
        // 大于当前时间
        if (runTime == null || runTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("需要一个未来的时间！");
        }

        final DelayTaskDto dto = new DelayTaskDto(clazz, params);

        final Long aLong = redis.zadd(Constant.REDIS_KEY, runTime.toEpochSecond(ZoneOffset.of("+8")), JSONObject.toJSONString(dto));
        return aLong != null && aLong > 0;
    }
}
