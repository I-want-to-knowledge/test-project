package com.geo.source.testmain.bean.spring_condition;

/**
 * 人
 *
 * @author YanZhen
 * @since 2019-03-28 15:24
 */
public class Person {
  private String name;
  private Integer age;

  Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public Integer getAge() {
    return age;
  }

  @Override
  public String toString() {
    return "Person{name='" + name + '\'' + ", age=" + age + '}';
  }
}
