package com.geo.source.spring_simple.example2.configuration;

import com.geo.source.spring_simple.example.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * spring configuration 注解
 * app-context-xml.xml
 *
 * @author YanZhen
 * @since 2019-05-16 14:14
 */
public class HelloWorldSpringAnnotated {
  public static void main(String[] args) {
    final ApplicationContext context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
    final MessageRenderer renderer = context.getBean("renderer3", MessageRenderer.class);
    renderer.render();
  }
}
