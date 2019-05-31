package com.geo.source.spring_simple.example5_AOP.pointcut.static_pointcut;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * 静态切入点，测试
 *
 * @author YanZhen
 * @since 2019-05-30 17:46
 */
public class StaticPointcutDemo {
  public static void main(String[] args) {
    final DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new SimpleStaticPointcut(), new SimpleAdvice());

    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.addAdvisor(advisor);
    proxyFactory.setTarget(new GoodGuitarist());
    final GoodGuitarist proxy1 = (GoodGuitarist) proxyFactory.getProxy();

    proxyFactory = new ProxyFactory();
    proxyFactory.addAdvisor(advisor);
    proxyFactory.setTarget(new GreatGuitarist());
    final GreatGuitarist proxy2 = (GreatGuitarist) proxyFactory.getProxy();

    proxy1.sing();
    proxy2.sing();

  }
}
