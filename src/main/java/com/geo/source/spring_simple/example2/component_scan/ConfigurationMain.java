package com.geo.source.spring_simple.example2.component_scan;

import com.geo.source.spring_simple.example.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 配置测试
 *
 * @author YanZhen
 * @since 2019-05-16 14:49
 */
public class ConfigurationMain {
  public static void main(String[] args) {
    // ComponentScan 路径注入
    final ApplicationContext context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
    final MessageRenderer renderer = context.getBean("renderer", MessageRenderer.class);
    renderer.render();

    // ImportResource xml注入
    final ApplicationContext context2 = new AnnotationConfigApplicationContext(HelloWorldConfiguration2.class);
    final MessageRenderer renderer2 = context2.getBean("renderer", MessageRenderer.class);
    renderer2.render();
  }
}
