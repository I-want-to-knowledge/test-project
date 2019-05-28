package com.geo.source.spring_simple.example4_chapter.groovy;

/**
 * groovy 实例
 *
 * @author YanZhen
 * @since 2019-05-27 18:32
 */
public class GroovyEntity {
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
    final StringBuffer sb = new StringBuffer("GroovyTest{");
    sb.append("name='").append(name).append('\'');
    sb.append(", age=").append(age);
    sb.append('}');
    return sb.toString();
  }
}
