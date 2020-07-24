package com.geo.source.redis.delaytask_v2.timer;

import com.geo.source.redis.delaytask_v2.DelayTaskConsumer;

/**
 * 延迟任务传递对象
 * @author YanZhen
 * @date 2020/04/24 13:28
 **/
public class DelayTaskDto {
    private Class<? extends DelayTaskConsumer> clazz;
    private String params;

    /**
     * 传递值
     * @param clazz 消费者实现类
     * @param params 传递参数
     */
    public DelayTaskDto(Class<? extends DelayTaskConsumer> clazz, String params) {
        this.clazz = clazz;
        this.params = params;
    }

    /**
     * @return 消费者实现类
     */
    public Class<? extends DelayTaskConsumer> getClazz() {
        return clazz;
    }

    /**
     * @return 传递参数
     */
    public String getParams() {
        return params;
    }
}
