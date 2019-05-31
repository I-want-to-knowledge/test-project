package com.geo.source.spring_simple.example5_AOP.pointcut.dynamic_pointcut;

import com.geo.source.spring_simple.example5_AOP.pointcut.static_pointcut.SimpleAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * 动态切入点，测试
 *
 * @author YanZhen
 * @since 2019-05-31 09:59
 */
public class DynamicPointcutDemo {
  public static void main(String[] args) {
    final DefaultPointcutAdvisor pointcutAdvisor = new DefaultPointcutAdvisor(new SampleDynamicPointcut(), new SimpleAdvice());
    final ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.addAdvisor(pointcutAdvisor);
    proxyFactory.setTarget(new SampleBean());
    final SampleBean proxy = (SampleBean) proxyFactory.getProxy();
    proxy.foo(1);
    proxy.foo(10);
    proxy.foo(100);

    proxy.bar();
    proxy.bar();
    proxy.bar();
  }
}
