package com.geo.source.spring_simple.example5_AOP.advice.simple_before_advice;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

/**
 * 调用方法前通知
 *
 * @author YanZhen
 * @since 2019-05-29 14:15
 */
public class SimpleBeforeAdvice implements MethodBeforeAdvice {

  public static void main(String[] args) {
    final ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.addAdvice(new SimpleBeforeAdvice());
    proxyFactory.setTarget(new Guitarist());
    final Guitarist proxy = (Guitarist) proxyFactory.getProxy();
    proxy.sing();
  }

  @Override
  public void before(Method method, Object[] args, Object target) throws Throwable {
    System.out.println("Before '" + method.getName() + "', tune guitar.");
    System.out.print("target 方法调用：");
    ((Guitarist)target).sing();
    System.out.print("Method 方法调用：");
    method.invoke(target, args);
  }
}
