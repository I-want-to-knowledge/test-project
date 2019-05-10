package com.geo.source.spring_simple.example;

/**
 * 消息提供者
 *
 * @author YanZhen
 * @since 2019-05-09 12:20
 */
@FunctionalInterface
public interface MessageProvider {
  String getMessage();
}
