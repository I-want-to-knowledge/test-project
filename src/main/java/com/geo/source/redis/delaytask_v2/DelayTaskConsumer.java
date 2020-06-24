package com.geo.source.redis.delaytask_v2;

import java.util.Map;

/**
 * 延迟任务-消费者，实现该类的实现类不需要注解
 * 注意：通过注入DelayTaskProducer类，使用producer方法发布消息
 *
 * @author YanZhen
 * @date 2020/04/16 00:20
 **/
@FunctionalInterface
public interface DelayTaskConsumer {
    /**
     * 消费任务
     * 注意：通过注入DelayTaskProducer类，使用producer方法生产延迟任务
     *
     * @param params 传参
     */
    void consumer(Map<String, Object> params);
}
