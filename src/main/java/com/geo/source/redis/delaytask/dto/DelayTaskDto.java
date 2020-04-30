package com.geo.source.redis.delaytask.dto;

import com.geo.source.redis.delaytask.DelayTaskConsumer;

import java.util.Map;

/**
 * @author YanZhen
 * @date 2020/04/24 13:28
 **/
public class DelayTaskDto {
    private Class<? extends DelayTaskConsumer> clazz;
    private Map<String, Object> params;

    public DelayTaskDto(Class<? extends DelayTaskConsumer> clazz, Map<String, Object> params) {
        this.clazz = clazz;
        this.params = params;
    }

    public Class<? extends DelayTaskConsumer> getClazz() {
        return clazz;
    }

    public Map<String, Object> getParams() {
        return params;
    }
}
