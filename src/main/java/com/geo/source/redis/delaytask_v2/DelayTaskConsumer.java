package com.geo.source.redis.delaytask_v2;

/**
 * 延迟任务-消费者
 * <pre>
 * 消费任务：实现该接口，并注入spring容器中（例：使用@Service）；
 * 如何生产任务：通过注入DelayTaskProducer类，使用producer方法生产延迟任务
 * </pre>
 *
 * @author YanZhen
 * @date 2020/04/16 00:20
 **/
@FunctionalInterface
public interface DelayTaskConsumer {
    /**
     * 实现该方法，消费任务；
     * 任务执行失败不可恢复，请做好补偿机制；
     *
     * @param params 传参
     */
    void consumer(String params);
}
