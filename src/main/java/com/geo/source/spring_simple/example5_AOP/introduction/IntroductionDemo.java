package com.geo.source.spring_simple.example5_AOP.introduction;

import org.springframework.aop.framework.ProxyFactory;

/**
 * 引入检查，测试
 *
 * @author YanZhen
 * @since 2019-06-05 09:45
 */
public class IntroductionDemo {
  public static void main(String[] args) {
    final Contact contact = new Contact();
    contact.setName("yz");
    final IsModifiedAdvisor advisor = new IsModifiedAdvisor();
    final ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.addAdvisor(advisor);
    proxyFactory.setTarget(contact);
    proxyFactory.setOptimize(true);// 强制使用CGLIB代理

    final Contact proxy = (Contact) proxyFactory.getProxy();
    final IsModified proxy1 = (IsModified) proxy;

    System.out.println("Is Contact?: " + (proxy instanceof Contact));
    System.out.println("Is IsModified?: " + (proxy instanceof IsModified));

    System.out.println("Has been modified?: " + proxy1.isModified());

    proxy.setName("yz");

    System.out.println("Has been modified?: " + proxy1.isModified());

    proxy.setName("yy");

    System.out.println("Has been modified?: " + proxy1.isModified());
  }
}
