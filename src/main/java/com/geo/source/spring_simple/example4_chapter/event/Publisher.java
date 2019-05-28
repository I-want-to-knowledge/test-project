package com.geo.source.spring_simple.example4_chapter.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 消息公布，应用程序事件，测试
 *
 * @author YanZhen
 * @since 2019-05-25 15:51
 */
public class Publisher implements ApplicationContextAware {

  private ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  public void publish(String msg) {
    applicationContext.publishEvent(new MessageEvent(this, msg));
  }
}

class PublishTest {

  public static void main(String[] args) {
    final ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring2/app-context-event.xml");
    final Publisher publisher = context.getBean("publisher", Publisher.class);
    publisher.publish("I send an SOS to the world... ");
    publisher.publish("... I hope that someone gets my...");
    publisher.publish("... Message in a bottle");
  }
}
