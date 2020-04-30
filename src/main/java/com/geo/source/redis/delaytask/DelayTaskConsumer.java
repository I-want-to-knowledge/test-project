package com.geo.source.redis.delaytask;

import java.util.Map;

/**
 * 延迟任务-消费
 * @author YanZhen
 * @date 2020/04/16 00:20
 **/
@FunctionalInterface
public interface DelayTaskConsumer {
    /**
     * 消费任务
     * @param params 传参
     */
    void consumer(Map<String, Object> params);
}
