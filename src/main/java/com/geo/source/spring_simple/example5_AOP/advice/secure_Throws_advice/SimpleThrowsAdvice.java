package com.geo.source.spring_simple.example5_AOP.advice.secure_Throws_advice;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

/**
 * TODO
 *
 * @author YanZhen
 * @since 2019-05-30 14:38
 */
public class SimpleThrowsAdvice implements ThrowsAdvice {

  public static void main(String[] args) {
    final ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.addAdvice(new SimpleThrowsAdvice());
    proxyFactory.setTarget(new ErrorBean());
    final ErrorBean proxy = (ErrorBean) proxyFactory.getProxy();
    try {
      proxy.ErrorProneMethod();
    } catch (Exception e) {
      System.out.println("过滤异常1！！");
    }
    try {
      proxy.otherErrorProneMethod();
    } catch (Exception e) {
      System.out.println("过滤异常2！！");
    }
  }

  /**
   * 该方法用public修饰
   * @param e 异常
   * @throws Throwable 异常
   */
  public void afterThrowing(Exception e) throws Throwable {
    System.out.println("***");
    System.out.println("Generic Exception Capture");
    System.out.println("Caught: " + e.getClass().getName());
    System.out.println("***\n");
  }

  /**
   * 该方法用public修饰
   * @param method 方法
   * @param args 参数
   * @param target 目标
   * @param e 异常
   * @throws Throwable 异常
   */
  public void afterThrowing(Method method, Object args, Object target, IllegalArgumentException e) throws Throwable {
    System.out.println("***");
    System.out.println("IllegalArgumentException Capture");
    System.out.println("Caught: " + e.getClass().getName());
    System.out.println("Method: " + method.getName());
    System.out.println("***\n");
  }
}
