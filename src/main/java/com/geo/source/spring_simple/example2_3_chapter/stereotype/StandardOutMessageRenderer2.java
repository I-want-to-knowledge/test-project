package com.geo.source.spring_simple.example2_3_chapter.stereotype;

import com.geo.source.spring_simple.example.MessageProvider;
import com.geo.source.spring_simple.example.MessageRenderer;
import com.geo.source.spring_simple.example.example1.StandardOutMessageRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * spring注解测试
 *
 * @author YanZhen
 * @since 2019-05-16 12:46
 */
@Service("renderer")
public class StandardOutMessageRenderer2 implements MessageRenderer {
  private MessageProvider messageProvider;

  @Override
  public void render() {
    if (messageProvider == null) {
      throw new RuntimeException("You must set the property messageProvider of class:"
              + StandardOutMessageRenderer.class.getName());
    }
    System.out.println(messageProvider.getMessage());
  }

  @Override
  @Autowired
  public void setMessageProvider(MessageProvider provider) {
    this.messageProvider = provider;
  }

  @Override
  public MessageProvider getMessageProvider() {
    return this.messageProvider;
  }
}
