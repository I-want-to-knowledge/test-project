package com.geo.source.spring_simple.example5_AOP.aop_framework.aop_namespace;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * aop 的命名空间
 *
 * @author YanZhen
 * @since 2019-06-05 18:04
 */
public class AopNamespaceDemo {
  public static void main(String[] args) {
    // m1();
    // m2();
    m3();
  }

  /**
   * 用SpEL表达式匹配通知的升级版
   * 环绕通知
   */
  private static void m3() {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext("spring3/aop_namespace-02.xml");
    final NewDocumentarist newDocumentarist2 = context.getBean("newDocumentarist2", NewDocumentarist.class);
    newDocumentarist2.execute();
    context.close();
  }

  /**
   * 用SpEL表达式匹配通知的升级版
   */
  private static void m2() {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext("spring3/aop_namespace-01.xml");
    final NewDocumentarist newDocumentarist1 = context.getBean("newDocumentarist1", NewDocumentarist.class);
    newDocumentarist1.execute();
    context.close();
  }

  /**
   * 用SpEL表达式匹配通知
   */
  private static void m1() {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext("spring3/aop_namespace.xml");
    final NewDocumentarist newDocumentarist = context.getBean("newDocumentarist", NewDocumentarist.class);
    newDocumentarist.execute();
    context.close();
  }
}
