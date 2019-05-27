package com.geo.source.spring_simple.example4_chapter.event;

import org.springframework.context.ApplicationEvent;

/**
 * 应用程序事件，测试
 * 消息事件
 *
 * @author YanZhen
 * @since 2019-05-25 15:39
 */
public class MessageEvent extends ApplicationEvent {

  private String msg;

  /**
   * Create a new ApplicationEvent.
   *
   * @param source the object on which the event initially occurred (never {@code null})
   * @param msg 消息
   */
  MessageEvent(Object source, String msg) {
    super(source);
    this.msg = msg;
  }

  public String getMsg() {
    return msg;
  }
}
