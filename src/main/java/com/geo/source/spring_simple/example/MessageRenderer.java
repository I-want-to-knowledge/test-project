package com.geo.source.spring_simple.example;

/**
 * 消息渲染器
 *
 * @author YanZhen
 * @since 2019-05-09 12:23
 */
public interface MessageRenderer {
  void render();
  void setMessageProvider(MessageProvider provider);
  MessageProvider getMessageProvider();
}
