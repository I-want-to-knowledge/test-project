package com.geo.source.spring_simple.example5_AOP.aspectj_integration.singleton_oriented;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 单例切面，测试
 *
 * @author YanZhen
 * @since 2019-06-10 10:31
 */
public class AspectJDemo {
  public static void main(String[] args) {
    final GenericXmlApplicationContext context =
            new GenericXmlApplicationContext();
    context.load("spring3/aop-context-singleton_aspect.xml");
    context.refresh();

    final MessageWriter messageWriter = new MessageWriter();
    messageWriter.writerMessage();
    messageWriter.foo();

    context.close();
  }
}
