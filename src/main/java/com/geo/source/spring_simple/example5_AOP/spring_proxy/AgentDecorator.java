package com.geo.source.spring_simple.example5_AOP.spring_proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 拦截器
 *
 * @author YanZhen
 * @since 2019-05-29 10:09
 */
public class AgentDecorator implements MethodInterceptor {

  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    System.out.print("James ");// 通知
    final Object proceed = invocation.proceed();// 连接点
    System.out.print("!");// 通知
    return proceed;
  }
}
