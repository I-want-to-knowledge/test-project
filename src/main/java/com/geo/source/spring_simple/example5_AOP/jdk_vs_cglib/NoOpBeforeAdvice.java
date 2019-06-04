package com.geo.source.spring_simple.example5_AOP.jdk_vs_cglib;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * JDK 与 CGLIB 性能比较
 *
 * @author YanZhen
 * @since 2019-06-03 16:41
 */
public class NoOpBeforeAdvice implements MethodBeforeAdvice {

  @Override
  public void before(Method method, Object[] args, Object target) throws Throwable {
    // no-op
  }
}
