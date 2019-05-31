package com.geo.source.spring_simple.example5_AOP.pointcut.static_name_pointcut;

import com.geo.source.spring_simple.example5_AOP.pointcut.static_pointcut.SimpleAdvice;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;

/**
 * 名称切入点
 *
 * @author YanZhen
 * @since 2019-05-31 11:03
 */
public class NamePointcutDemo {
  public static void main(String[] args) {
    //m1();
    m2();
  }

  /**
   * 方便版本
   */
  private static void m2() {
    final NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor(new SimpleAdvice());
    advisor.addMethodName("sing").addMethodName("rest");

    commonality(advisor);
  }

  /**
   * 原始版本
   */
  private static void m1() {
    final NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
    nameMatchMethodPointcut.addMethodName("sing").addMethodName("rest");

    final DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(nameMatchMethodPointcut, new SimpleAdvice());

    commonality(advisor);
  }

  private static void commonality(PointcutAdvisor advisor) {
    final ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.addAdvisor(advisor);
    proxyFactory.setTarget(new GrammyGuitarist());

    final GrammyGuitarist proxy = (GrammyGuitarist) proxyFactory.getProxy();
    proxy.sing();
    proxy.sing(new Guitar());
    proxy.rest();
    proxy.talk();
  }
}
