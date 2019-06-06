package com.geo.source.spring_simple.example5_AOP.aop_framework.proxy_factory_bean;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * ProxyFactoryBean 测试
 *
 * @author YanZhen
 * @since 2019-06-05 16:04
 */
public class ProxyFactoryBeanDemo {

  /**
   * ERROR 实例
   * @param args 参数
   */
  public static void main(String[] args) {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("spring3/app-context-aop-framework.xml");
    context.refresh();

    final Documentarist documentaristOne = context.getBean("documentaristOne", Documentarist.class);
    final Documentarist documentaristTwo = context.getBean("documentaristTwo", Documentarist.class);

    System.out.println("Documentarist One >>");
    documentaristOne.execute();

    System.out.println("\nDocumentarist Two >>");
    documentaristTwo.execute();
  }
}
