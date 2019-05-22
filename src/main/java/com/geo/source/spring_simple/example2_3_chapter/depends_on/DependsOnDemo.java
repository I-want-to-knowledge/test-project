package com.geo.source.spring_simple.example2_3_chapter.depends_on;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 解析依赖项，实例
 *
 * @author YanZhen
 * @since 2019-05-20 17:39
 */
public class DependsOnDemo {

  @Configuration
  @ComponentScan("com.geo.source.spring_simple.example2_3_chapter.depends_on")
  static class DependsOnConfig {
  }

  public static void main(String[] args) {
    // xml();
    annotation();
  }

  /**
   * 以注解的形式注入
   */
  private static void annotation() {
    final GenericApplicationContext context = new AnnotationConfigApplicationContext(DependsOnConfig.class);
    final DependsOnTest2 dependsOnTest2 = context.getBean("dependsOnTest2", DependsOnTest2.class);
    dependsOnTest2.sing();
    context.close();
  }

  /**
   * 以xml的形式注入
   */
  private static void xml() {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:spring/app-context-xml.xml");
    context.refresh();
    final DependsOnTest dependsOnTest = context.getBean("dependsOnTest", DependsOnTest.class);
    dependsOnTest.sing();
    context.close();
  }
}
