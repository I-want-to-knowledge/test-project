package com.geo.source.spring_simple.example5_AOP.jdk_vs_cglib;

/**
 * JDK 与 CGLIB 性能比较
 *
 * @author YanZhen
 * @since 2019-06-03 16:28
 */
public interface SimpleBean {
  void advised();

  void unadvised();
}
