package com.geo.source.spring_simple.example5_AOP.pointcut;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * 匹配信息
 *
 * @author YanZhen
 * @since 2019-05-30 17:40
 */
public class SimpleStaticPointcut extends StaticMethodMatcherPointcut {

  @Override
  public boolean matches(Method method, Class<?> targetClass) {
    return "sing".equals(method.getName());
  }

  @Override
  public ClassFilter getClassFilter() {
    return clazz -> clazz == GoodGuitarist.class;
  }
}
