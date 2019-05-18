package com.geo.source.spring_simple.example2.configuration;

import com.geo.source.spring_simple.example.MessageProvider;
import com.geo.source.spring_simple.example.MessageRenderer;
import com.geo.source.spring_simple.example2.stereotype.HelloWorldMessageProvider;
import com.geo.source.spring_simple.example2.stereotype.StandardOutMessageRenderer2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * configuration 配置
 *
 * @author YanZhen
 * @since 2019-05-16 14:11
 */
@Configuration
public class HelloWorldConfiguration {

  @Bean
  public MessageProvider provider3() {
    return new HelloWorldMessageProvider();
  }

  @Bean
  public MessageRenderer renderer3() {
    final StandardOutMessageRenderer2 messageRenderer2 = new StandardOutMessageRenderer2();
    messageRenderer2.setMessageProvider(provider3());// DI
    return messageRenderer2;
  }
}
