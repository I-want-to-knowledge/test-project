package com.geo.source.testmain.publictest;

/**
 * 测试对象
 *
 * @author YanZhen
 * @since 2019-04-27 09:25
 */
public class Aa {

  private String id;
  private String name;

  public Aa(String id, String name) {
    this.id = id;
    this.name = name;
  }

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
    System.out.println(getClass().getName() + "@" + Integer.toHexString(hashCode()));
    return "{'id':" + id + ", 'name':" + name + "}";
  }
}
