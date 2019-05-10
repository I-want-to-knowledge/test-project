package com.geo.source.spring_simple.example.example3;

import com.geo.source.spring_simple.example.MessageProvider;

/**
 * 消息
 *
 * @author YanZhen
 * @since 2019-05-09 15:27
 */
public class HelloMessage implements MessageProvider {

  @Override
  public String getMessage() {
    return "默认输入字符串！";
  }
}
