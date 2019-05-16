package com.geo.source.spring_simple.example2.stereotype;

import com.geo.source.spring_simple.example.MessageProvider;
import org.springframework.stereotype.Component;

/**
 * spring注解测试
 *
 * @author YanZhen
 * @since 2019-05-16 12:44
 */
@Component("provider")
public class HelloWorldMessageProvider implements MessageProvider {

  @Override
  public String getMessage() {
    return "Hello world!";
  }
}
