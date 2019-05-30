package com.geo.source.spring_simple.example5_AOP.advice.secure_before_advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * 代理，方法前通知
 *
 * @author YanZhen
 * @since 2019-05-29 16:10
 */
public class SecurityAdvice implements MethodBeforeAdvice {

  private SecurityManager securityManager;

  public SecurityAdvice() {
    this.securityManager = new SecurityManager();
  }

  @Override
  public void before(Method method, Object[] args, Object target) throws Throwable {
    final UserInfo userInfo = securityManager.getLoggedOnUser();
    if (userInfo == null) {
      System.out.println("No user authenticated");
      throw new SecurityException("You must login before attempting to invoke the method: " + method.getName());
    } else if ("John".equals(userInfo.getName())) {
      System.out.println("Logged in user is John - OKAY!");
    } else {
      System.out.println("Logged in user is " + userInfo.getName() + " NOT GOOD :(");
      throw new SecurityException("User " + userInfo.getName() + " is not allowed access to method " + method.getName());
    }
  }
}
