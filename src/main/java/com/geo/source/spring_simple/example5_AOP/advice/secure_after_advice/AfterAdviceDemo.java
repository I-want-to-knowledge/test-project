package com.geo.source.spring_simple.example5_AOP.advice.secure_after_advice;

import org.springframework.aop.framework.ProxyFactory;

/**
 * 方法返回后通知，测试
 *
 * @author YanZhen
 * @since 2019-05-29 17:54
 */
public class AfterAdviceDemo {
  public static void main(String[] args) {
    final KeyGenerator proxy;
    proxy = getKeyGenerator();

    for (int i = 0; i < 10; i++) {
      try {
        final long key = proxy.getKey();
        System.out.println("key : " + key);
      } catch (Exception e) {
        System.err.println("Weak Key Generated err : " + e.getMessage());
      }
    }
  }

  private static KeyGenerator getKeyGenerator() {
    final ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.addAdvice(new WeakKeyCheckAdvice());
    proxyFactory.setTarget(new KeyGenerator());
    return (KeyGenerator) proxyFactory.getProxy();
  }
}
