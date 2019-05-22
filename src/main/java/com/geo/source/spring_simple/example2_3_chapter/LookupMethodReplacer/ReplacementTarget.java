package com.geo.source.spring_simple.example2_3_chapter.LookupMethodReplacer;

/**
 * spring方法替换，测试！
 * 原始方法
 *
 * @author YanZhen
 * @since 2019-05-18 15:50
 */
public class ReplacementTarget {
  String formatMessage(String msg) {
    return "<h1>" + msg + "</h1>";
  }

  String formatMessage(Object msg) {
    return "<h1>" + msg + "</h1>";
  }
}
