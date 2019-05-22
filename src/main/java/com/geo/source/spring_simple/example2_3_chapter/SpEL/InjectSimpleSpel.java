package com.geo.source.spring_simple.example2_3_chapter.SpEL;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 被注入者
 * app-context.xml
 *
 * @author YanZhen
 * @since 2019-05-16 17:19
 */
public class InjectSimpleSpel {
  private String name;
  private int age;
  private float height;
  private boolean programmer;
  private Long ageInSeconds;

  public static void main(String[] args) {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:spring/app-context.xml");
    context.refresh();
    final InjectSimpleSpel injectSimpleSpel = (InjectSimpleSpel) context.getBean("injectSimpleSpel");
    System.out.println(injectSimpleSpel);
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public float getHeight() {
    return height;
  }

  public boolean isProgrammer() {
    return programmer;
  }

  public Long getAgeInSeconds() {
    return ageInSeconds;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void setHeight(float height) {
    this.height = height;
  }

  public void setProgrammer(boolean programmer) {
    this.programmer = programmer;
  }

  public void setAgeInSeconds(Long ageInSeconds) {
    this.ageInSeconds = ageInSeconds;
  }

  @Override
  public String toString() {
    return "name:" + name + "\nage:" + age + "\nheight:" + height
            + "\nprogrammer:" + programmer + "\nageInSeconds:" + ageInSeconds;
  }
}
