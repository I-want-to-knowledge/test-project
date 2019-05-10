package com.geo.source.spring_simple.example.example2;

import com.geo.source.spring_simple.example.MessageProvider;
import com.geo.source.spring_simple.example.MessageRenderer;

import java.io.IOException;
import java.util.Properties;

/**
 * 消息支持工厂
 *
 * @author YanZhen
 * @since 2019-05-09 15:01
 */
public class MessageSupportFactory {

  private static MessageSupportFactory instance;

  private Properties properties;
  private MessageRenderer messageRenderer;
  private MessageProvider messageProvider;

  public MessageSupportFactory() {
    this.properties = new Properties();
    try {
      this.properties.load(this.getClass().getResourceAsStream("/test.properties"));
      this.messageRenderer = (MessageRenderer) Class.forName(properties.getProperty("renderer.class")).newInstance();
      this.messageProvider = () -> "这是一段函数式！";// (MessageProvider) Class.forName(properties.getProperty("provider.class")).newInstance();
    } catch (IOException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  static {
    instance = new MessageSupportFactory();
  }

  public static MessageSupportFactory getInstance() {
    return instance;
  }

  public MessageRenderer getMessageRenderer() {
    return messageRenderer;
  }

  public MessageProvider getMessageProvider() {
    return messageProvider;
  }
}
