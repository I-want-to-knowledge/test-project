package com.geo.source.spring_simple.example5_AOP.advice.secure_after_advice;

import com.geo.source.spring_simple.example5_AOP.advice.simple_before_advice.Guitarist;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

/**
 * 简单，方法返回后通知
 *
 * @author YanZhen
 * @since 2019-05-29 17:18
 */
public class SimpleAfterReturningAdvice implements AfterReturningAdvice {

  public static void main(String[] args) {
    final ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.addAdvice(new SimpleAfterReturningAdvice());
    proxyFactory.setTarget(new Guitarist());
    final Guitarist proxy = (Guitarist) proxyFactory.getProxy();
    proxy.sing();
  }

  @Override
  public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
    System.out.println("After '" + method.getName() + "' put down guitar.");
    System.out.println("返回值为："+returnValue);
  }
}
