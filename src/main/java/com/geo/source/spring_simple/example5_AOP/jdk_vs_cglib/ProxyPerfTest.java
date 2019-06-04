package com.geo.source.spring_simple.example5_AOP.jdk_vs_cglib;

import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * JDK 与 CGLIB 性能比较
 *
 * @author YanZhen
 * @since 2019-06-03 16:42
 */
public class ProxyPerfTest {
  public static void main(String[] args) {
    final SimpleBean simpleBean = new DefaultSimpleBean();
    final DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new TestPointcut(), new NoOpBeforeAdvice());
    runCglibTests(simpleBean, advisor);
    runCglibFrozenTests(simpleBean, advisor);
    runJdkTests(simpleBean, advisor);
  }

  /**
   * 一个JDK代理
   * @param simpleBean 被代理的对象
   * @param advisor 顾问
   */
  private static void runJdkTests(SimpleBean simpleBean, DefaultPointcutAdvisor advisor) {
    final ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.addAdvisor(advisor);
    proxyFactory.setTarget(simpleBean);
    proxyFactory.setInterfaces(SimpleBean.class);

    final SimpleBean proxy = (SimpleBean) proxyFactory.getProxy();
    System.out.println("Running JDK Tests");
    test(proxy);
  }

  /**
   * 具有冻结通知链的CGLIB代理
   * @param simpleBean 被代理的对象
   * @param advisor 顾问
   */
  private static void runCglibFrozenTests(SimpleBean simpleBean, DefaultPointcutAdvisor advisor) {
    final ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.setProxyTargetClass(true);
    proxyFactory.addAdvisor(advisor);
    proxyFactory.setTarget(simpleBean);
    proxyFactory.setFrozen(true);

    final SimpleBean proxy = (SimpleBean) proxyFactory.getProxy();
    System.out.println("Running CGLIB (Frozen) Tests");
    test(proxy);
  }

  /**
   * 一个标准的CGLIB代理
   * @param simpleBean 被代理的对象
   * @param advisor 顾问
   */
  private static void runCglibTests(SimpleBean simpleBean, DefaultPointcutAdvisor advisor) {
    final ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.setProxyTargetClass(true);
    proxyFactory.addAdvisor(advisor);
    proxyFactory.setTarget(simpleBean);
    // proxyFactory.setOptimize(true);

    final SimpleBean proxy = (SimpleBean) proxyFactory.getProxy();
    System.out.println("Running CGLIB (Standard) Tests");
    test(proxy);
  }

  private static void test(SimpleBean proxy) {
    long before = 0;
    long after = 0;
    System.out.println("Testing Advised() Method");
    before = System.currentTimeMillis();
    for (int i = 0; i < 500000; i++) {
      proxy.advised();
    }
    after = System.currentTimeMillis();

    System.out.println("Took " + (before - after) + " ms");

    System.out.println("Testing unAdvised() Method");
    before = System.currentTimeMillis();
    for (int i = 0; i < 500000; i++) {
      proxy.unadvised();
    }
    after = System.currentTimeMillis();

    System.out.println("Took " + (before - after) + " ms");

    System.out.println("Testing equals() Method");
    before = System.currentTimeMillis();
    for (int i = 0; i < 500000; i++) {
      proxy.equals(proxy);
    }
    after = System.currentTimeMillis();

    System.out.println("Took " + (before - after) + " ms");

    System.out.println("Testing hashCode() Method");
    before = System.currentTimeMillis();
    for (int i = 0; i < 500000; i++) {
      proxy.hashCode();
    }
    after = System.currentTimeMillis();

    System.out.println("Took " + (before - after) + " ms");

    final Advised advised = (Advised) proxy;
    System.out.println("Testing Advised.getProxyTargetClass() Method");
    before = System.currentTimeMillis();
    for (int i = 0; i < 500000; i++) {
      advised.getTargetClass();
    }
    after = System.currentTimeMillis();

    System.out.println("Took " + (before - after) + " ms");
    System.out.println(">>>\n");
  }
}
