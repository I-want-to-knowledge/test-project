package com.geo.source.spring_simple.example4_chapter.property_source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 属性文件注入
 *
 * @author YanZhen
 * @since 2019-05-27 11:02
 */
@Configuration
@PropertySource("classpath:test.properties")
public class APPConfig {

  @Autowired
  Environment environment;

  @Bean
  EnvTest envTest() {
    return new EnvTest(environment.getProperty("stor"));
  }
}