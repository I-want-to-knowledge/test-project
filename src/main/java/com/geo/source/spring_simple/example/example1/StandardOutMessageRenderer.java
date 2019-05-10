package com.geo.source.spring_simple.example.example1;

import com.geo.source.spring_simple.example.MessageProvider;
import com.geo.source.spring_simple.example.MessageRenderer;

/**
 * 标准输出消息渲染
 *
 * @author YanZhen
 * @since 2019-05-09 12:30
 */
public class StandardOutMessageRenderer implements MessageRenderer {
  private MessageProvider messageProvider;

  @Override
  public void render() {
    if (messageProvider == null) {
      throw new IllegalArgumentException("Message Provider is required!");
    }
    System.out.println(messageProvider.getMessage());
  }

  @Override
  public void setMessageProvider(MessageProvider provider) {
    this.messageProvider = provider;
  }

  @Override
  public MessageProvider getMessageProvider() {
    return this.messageProvider;
  }
}
