package com.geo.source.spring_simple.resource_VS_autowired;

/**
 * Resource 与 Autowired 注解测试
 *
 * @author YanZhen
 * @since 2019-05-17 14:49
 */
public class ResourceAutowired {
  private String a;

  ResourceAutowired(String a) {
    this.a = a;
  }

  @Override
  public String toString() {
    return a;
  }
}
