package com.geo.source.redis.delaytask;

import java.util.Map;

/**
 * @author YanZhen
 * @date 2020/04/24 13:28
 **/
public class DelayTaskDto {
    private Class<DelayTaskConsumer> clazz;
    private Map<String, Object> params;

    public DelayTaskDto(Class<DelayTaskConsumer> clazz, Map<String, Object> params) {
        this.clazz = clazz;
        this.params = params;
    }

    public Class<DelayTaskConsumer> getClazz() {
        return clazz;
    }

    public Map<String, Object> getParams() {
        return params;
    }
}
