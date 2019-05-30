package com.geo.source.spring_simple.example5_AOP.advice.secure_before_advice;

import org.springframework.aop.framework.ProxyFactory;

/**
 * 安全测试
 *
 * @author YanZhen
 * @since 2019-05-29 16:23
 */
public class SecurityDemo {
  public static void main(String[] args) {
    SecureBean secureBean = getSecureBean();
    final SecurityManager securityManager = new SecurityManager();
    securityManager.login("John", "pwd");
    secureBean.writeSecureMessage();
    securityManager.logout();

    try {
      securityManager.login("invalid user", "pwd");
      secureBean.writeSecureMessage();
    } catch (SecurityException e) {
      System.err.println("Exception Caught: " + e.getMessage());;
    } finally {
      securityManager.logout();
    }

    try {
      secureBean.writeSecureMessage();
    } catch (Exception e) {
      System.err.println("Exception Caught: " + e.getMessage());
    }
  }

  private static SecureBean getSecureBean() {
    final ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.addAdvice(new SecurityAdvice());
    proxyFactory.setTarget(new SecureBean());
    return (SecureBean) proxyFactory.getProxy();
  }
}
