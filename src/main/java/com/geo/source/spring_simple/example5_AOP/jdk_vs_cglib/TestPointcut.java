package com.geo.source.spring_simple.example5_AOP.jdk_vs_cglib;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * JDK 与 CGLIB 性能比较
 *
 * @author YanZhen
 * @since 2019-06-03 16:38
 */
public class TestPointcut extends StaticMethodMatcherPointcut {

  @Override
  public boolean matches(Method method, Class<?> targetClass) {
    return "advice".equals(method.getName());
  }
}
