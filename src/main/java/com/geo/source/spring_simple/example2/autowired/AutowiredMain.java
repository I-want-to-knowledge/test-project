package com.geo.source.spring_simple.example2.autowired;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Autowired 注入测试
 *
 * @author YanZhen
 * @since 2019-05-16 16:15
 */
public class AutowiredMain {
  public static void main(String[] args) {
    final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutowiredConfiguration.class);
    final Singer singer = context.getBean(Singer.class);
    singer.sing();
  }
}
