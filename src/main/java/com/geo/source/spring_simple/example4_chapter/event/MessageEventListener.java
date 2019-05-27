package com.geo.source.spring_simple.example4_chapter.event;

import org.springframework.context.ApplicationListener;

/**
 * 消息事件监听器
 *
 * @author YanZhen
 * @since 2019-05-25 15:43
 */
public class MessageEventListener implements ApplicationListener<MessageEvent> {

  @Override
  public void onApplicationEvent(MessageEvent event) {
    System.out.println("Received: " + event.getMsg());
  }
}

class MessageEventListener2 implements ApplicationListener<MessageEvent> {

  @Override
  public void onApplicationEvent(MessageEvent event) {
    System.out.println("Received2: " + event.getMsg());
  }
}