package com.geo.source.spring_simple.example5_AOP.pointcut.annotation_pointcut;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解，切入点测试
 *
 * @author YanZhen
 * @since 2019-05-31 15:13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface AdviceRequired {
}
