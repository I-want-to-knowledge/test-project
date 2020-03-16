package com.geo.source.csv.service;

/**
 * @author YanZhen
 * @date 2020/03/16 11:11
 **/
@FunctionalInterface
public interface FieldAdaptor {
    /**
     * 处理字段
     * @param <T> 字段值类型
     * @param t 字段值
     * @return 返回字段值的字符串类型
     */
    <T> String process(T t);
}
