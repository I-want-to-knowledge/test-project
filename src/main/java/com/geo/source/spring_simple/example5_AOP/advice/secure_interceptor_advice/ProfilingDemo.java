package com.geo.source.spring_simple.example5_AOP.advice.secure_interceptor_advice;

import org.springframework.aop.framework.ProxyFactory;

/**
 * 环绕通知，分析
 *
 * @author YanZhen
 * @since 2019-05-30 13:34
 */
public class ProfilingDemo {
  public static void main(String[] args) {
    final ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.addAdvice(new ProfilingInterceptor());
    proxyFactory.setTarget(new WorkerBean());
    final WorkerBean proxy = (WorkerBean) proxyFactory.getProxy();
    proxy.doSomeWork(10_000_000);
    proxy.doSomeWork(10);
  }
}
