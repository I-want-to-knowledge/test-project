package com.geo.source.spring_simple.example5_AOP.pointcut.dynamic_pointcut;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * 动态切入点，测试
 *
 * @author YanZhen
 * @since 2019-05-31 09:50
 */
public class SampleDynamicPointcut extends DynamicMethodMatcherPointcut {
  @Override
  public boolean matches(Method method, Class<?> targetClass) {
    System.out.println("Static check for " + method.getName());
    return "foo".equals(method.getName());
  }

  @Override
  public boolean matches(Method method, Class<?> targetClass, Object... args) {
    System.out.println("Dynamic check for " + method.getName());
    return (int) args[0] != 100;
  }

  @Override
  public ClassFilter getClassFilter() {
    return clazz -> clazz == SampleBean.class;
  }
}
