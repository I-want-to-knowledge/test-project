package com.geo.source.exception.simple_example;

/**
 * 自定义断言
 * @author YanZhen
 * @date 2020/05/08 13:24
 **/
public interface SimpleCustomAssert {
    /**
     * 创建异常
     * @return 异常信息
     */
    SimpleBaseException newException();

    /**
     * 创建异常
     * @param args 参数
     * @return 异常信息
     */
    SimpleBaseException newException(Object args);

    /**
     * 创建异常
     * @param t 异常
     * @param args 参数
     * @return 异常信息
     */
    SimpleBaseException newException(Throwable t, Object args);

    /**
     * 是否为空，抛出异常
     * @param o 判断对象
     */
    default void assertNotNull(Object o) {
        if (o == null) {
            throw newException();
        }
    }

    /**
     * 是否为空，抛出异常
     * @param o 判断对象
     * @param args 参数
     */
    default void assertNotNull(Object o, Object args) {
        if (o == null) {
            throw newException(args);
        }
    }
}
