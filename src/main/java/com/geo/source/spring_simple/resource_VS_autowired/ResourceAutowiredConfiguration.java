package com.geo.source.spring_simple.resource_VS_autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

/**
 * Resource 与 Autowired 注解测试，是否有重复注入！
 *
 * @author YanZhen
 * @since 2019-05-17 14:57
 */
@ComponentScan("com.geo.source.spring_simple.resource_VS_autowired")
@Configuration
public class ResourceAutowiredConfiguration {

  @Bean
  public ResourceAutowired resourceAutowired() {
    return new ResourceAutowired("123456");
  }

  @Bean
  public ResourceAutowired resourceAutowired2() {
    return new ResourceAutowired("987654");
  }

  @Bean
  public List<ResourceAutowired> resourceAutowiredList() {
    return Collections.singletonList(new ResourceAutowired("00000"));
  }
}
