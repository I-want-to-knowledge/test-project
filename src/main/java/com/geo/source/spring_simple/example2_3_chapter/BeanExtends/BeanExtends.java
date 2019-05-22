package com.geo.source.spring_simple.example2_3_chapter.BeanExtends;

/**
 * Bean 继承，测试
 *
 * @author YanZhen
 * @since 2019-05-21 16:11
 */
public class BeanExtends {

  private String name;
  private int age;

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "name:" + name + "\nage:" + age;
  }
}
