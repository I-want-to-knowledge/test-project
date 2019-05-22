package com.geo.source.spring_simple.example2_3_chapter.AutomaticAssembly;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 自动装配bean，注解Bean注入
 *
 * @author YanZhen
 * @since 2019-05-21 15:18
 */
@Configuration
public class Target4Configuration {

  @Bean
  Foo2 foo2Impl1() {
    return new FooImpl1();
  }

  @Bean
  Foo2 foo2Impl2() {
    return new FooImpl2();
  }

  @Bean
  Bar bar4() {
    return new Bar();
  }

  @Bean
  Target3 target3() {
    return new Target3();
  }

  public static void main(String[] args) {
    final GenericApplicationContext context = new AnnotationConfigApplicationContext(Target4Configuration.class);
    final Target3 target3 = context.getBean(Target3.class);
    context.close();
  }
}
