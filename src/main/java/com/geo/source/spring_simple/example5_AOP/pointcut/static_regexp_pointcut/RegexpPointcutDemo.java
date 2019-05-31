package com.geo.source.spring_simple.example5_AOP.pointcut.static_regexp_pointcut;

import com.geo.source.spring_simple.example5_AOP.pointcut.static_pointcut.SimpleAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;

/**
 * 正则切入点匹配，测试
 *
 * @author YanZhen
 * @since 2019-05-31 11:33
 */
public class RegexpPointcutDemo {
  public static void main(String[] args) {
    final JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
    pointcut.setPattern(".*sing.*");

    final DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleAdvice());

    final ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.addAdvisor(advisor);
    proxyFactory.setTarget(new Guitarist());

    final Guitarist proxy = (Guitarist) proxyFactory.getProxy();
    proxy.sing();
    proxy.sing2();
    proxy.rest();
  }
}
