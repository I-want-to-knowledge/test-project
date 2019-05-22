package com.geo.source.spring_simple.CircularBeanTest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * setter 注入循环依赖
 *
 * @author YanZhen
 * @since 2019-05-21 18:14
 */
@Configuration
public class SetterInject {

  @Bean
  AA aa() {
    return new AA();
  }

  @Bean
  BB bb() {
    return new BB();
  }

  @Bean
  CC cc() {
    return new CC();
  }
}
