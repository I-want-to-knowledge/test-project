package com.geo.source.spring_simple.example5_AOP.pointcut.static_pointcut;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 环绕通知
 *
 * @author YanZhen
 * @since 2019-05-30 17:43
 */
public class SimpleAdvice implements MethodInterceptor {

  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    System.out.println(">> Invoking " + invocation.getMethod().getName());
    final Object o = invocation.proceed();
    System.out.println(">> Done \n");
    return o;
  }
}
