package com.geo.source.spring_simple.example;

import com.geo.source.spring_simple.example.example1.StandardOutMessageRenderer;
import com.geo.source.spring_simple.example.example2.MessageSupportFactory;
import com.geo.source.spring_simple.example.example4.HelloConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 消息
 *
 * @author YanZhen
 * @since 2019-05-09 12:34
 */
public class MessageMain {
  public static void main(String[] args) {
//    m1();
//    m2();
//    m3();
    m4();
  }

  private static void m4() {
    final ApplicationContext context = new AnnotationConfigApplicationContext(HelloConfiguration.class);
    final MessageRenderer messageRenderer = context.getBean("messageRenderer", MessageRenderer.class);
    messageRenderer.render();
  }

  /**
   * example3测试
   */
  private static void m3() {
    final ApplicationContext context =
            new ClassPathXmlApplicationContext("spring/app-context.xml");
    final MessageRenderer renderer = context.getBean("renderer", MessageRenderer.class);
    renderer.render();
  }

  /**
   * 对example2的测试
   */
  private static void m2() {
    final MessageSupportFactory instance = MessageSupportFactory.getInstance();
    final MessageRenderer renderer = instance.getMessageRenderer();
    renderer.setMessageProvider(instance.getMessageProvider());
    renderer.render();
  }

  /**
   * 对example1的测试
   */
  private static void m1() {
    final MessageRenderer messageRenderer = new StandardOutMessageRenderer();
    messageRenderer.setMessageProvider(() -> "你好！");
    messageRenderer.render();
  }
}
