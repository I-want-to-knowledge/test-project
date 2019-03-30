package com.geo.source.testmain.bean;

/**
 * 测试对象
 *
 * @author YanZhen
 * @since 2019-03-25 12:41
 */
public class Abc {
  private String id;
  private String name;

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "{'id':" + id + ", 'name':" + name + "}";
  }
}
