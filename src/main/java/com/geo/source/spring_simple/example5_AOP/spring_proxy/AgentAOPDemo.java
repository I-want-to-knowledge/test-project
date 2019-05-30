package com.geo.source.spring_simple.example5_AOP.spring_proxy;

import org.springframework.aop.framework.ProxyFactory;

/**
 * 代理 AOP
 *
 * @author YanZhen
 * @since 2019-05-29 10:14
 */
public class AgentAOPDemo {
  public static void main(String[] args) {
    final ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.addAdvice(new AgentDecorator());// 通过AgentDecorator通知传递给ProxyFactory
    proxyFactory.setTarget(new Agent());// 织入指定目标
    final Agent proxy = (Agent) proxyFactory.getProxy();
    proxy.speak();
  }
}
