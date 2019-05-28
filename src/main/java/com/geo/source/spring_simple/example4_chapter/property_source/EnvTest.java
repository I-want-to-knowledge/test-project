package com.geo.source.spring_simple.example4_chapter.property_source;

/**
 * 属性文件注入
 *
 * @author YanZhen
 * @since 2019-05-27 11:05
 */
public class EnvTest {
  private String stor;

  public EnvTest(String stor) {
    this.stor = stor;
  }

  public String getStor() {
    return stor;
  }
}
