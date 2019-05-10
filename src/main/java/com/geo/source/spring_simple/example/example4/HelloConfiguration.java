package com.geo.source.spring_simple.example.example4;

import com.geo.source.spring_simple.example.MessageRenderer;
import com.geo.source.spring_simple.example.example1.StandardOutMessageRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 *
 * @author YanZhen
 * @since 2019-05-09 16:02
 */
@Configuration
public class HelloConfiguration {

  @Bean
  public MessageRenderer renderer() {
    final MessageRenderer renderer = new StandardOutMessageRenderer();
    renderer.setMessageProvider(() -> "已注入的形式");
    return renderer;
  }
}
