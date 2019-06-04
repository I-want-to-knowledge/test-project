package com.geo.source.spring_simple.example5_AOP.jdk_vs_cglib;

/**
 * JDK 与 CGLIB 性能比较
 *
 * @author YanZhen
 * @since 2019-06-03 16:36
 */
public class DefaultSimpleBean implements SimpleBean {
  private long dummy = 0;

  @Override
  public void advised() {
    dummy = System.currentTimeMillis();
  }

  @Override
  public void unadvised() {
    dummy = System.currentTimeMillis();
  }
}
