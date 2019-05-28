package com.geo.source.spring_simple.example4_chapter.groovy;

import org.springframework.context.support.GenericGroovyApplicationContext;

/**
 * spring 使用groovy
 *
 * @author YanZhen
 * @since 2019-05-27 18:21
 */
public class UsingGroovy {
  public static void main(String[] args) {
    m1();
    m2();
  }

  /**
   * 用groovy语言调用获取Bean
   */
  private static void m2() {
    new GenericGroovyApplicationContext("classpath:spring2/groovy/beans2.groovy");
  }

  /**
   * 加载实例
   */
  private static void m1() {
    final GenericGroovyApplicationContext context = new GenericGroovyApplicationContext("classpath:spring2/groovy/beans.groovy");
    final GroovyEntity groovyEntity = context.getBean("groovyEntity", GroovyEntity.class);
    System.out.println(groovyEntity);
  }
}