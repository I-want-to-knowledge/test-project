package com.geo.source.spring_simple.CircularBeanTest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 构造函数 注入循环依赖
 * UnsatisfiedDependencyException
 *
 * @author YanZhen
 * @since 2019-05-21 18:14
 */
@Configuration
public class ConstructorInject {
  @Bean
  AA aa(BB bb) {
    return new AA(bb);
  }

  @Bean
  BB bb(CC cc) {
    return new BB(cc);
  }

  @Bean
  CC cc(AA aa) {
    return new CC(aa);
  }
}
