package com.geo.source.spring_simple.example4_chapter.property_source;

import com.geo.source.spring_simple.example4_chapter.event.Publisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 属性注入测试
 *
 * @author YanZhen
 * @since 2019-05-27 11:26
 */
class EnvironmentTest {
  public static void main(String[] args) {
    // m1();
    m2();
  }

  /**
   * 通过 import 间接注入
   */
  private static void m2() {
    final GenericApplicationContext context = new AnnotationConfigApplicationContext(APPConfig2.class);
    final EnvTest envTest = context.getBean("envTest", EnvTest.class);
    System.out.println(envTest.getStor());
    final Publisher publisher = context.getBean("publisher", Publisher.class);
    publisher.publish("I send an SOS to the world... ");
    publisher.publish("... I hope that someone gets my...");
    publisher.publish("... Message in a bottle");
    context.close();
  }

  /**
   * 直接注入
   */
  private static void m1() {
    final GenericApplicationContext context = new AnnotationConfigApplicationContext(APPConfig.class);
    final EnvTest envTest = context.getBean("envTest", EnvTest.class);
    System.out.println(envTest.getStor());
    context.close();
  }
}
