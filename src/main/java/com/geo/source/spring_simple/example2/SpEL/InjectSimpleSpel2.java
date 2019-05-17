package com.geo.source.spring_simple.example2.SpEL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * 被注入者
 * app-context-annotation.xml
 *
 * @author YanZhen
 * @since 2019-05-16 17:19
 */
@Service
public class InjectSimpleSpel2 {
  @Value("#{injectSimpleConfig2.name}")
  private String name;
  @Value("#{injectSimpleConfig2.age + 1}")
  private int age;
  @Value("#{injectSimpleConfig2.height}")
  private float height;
  @Value("#{injectSimpleConfig2.programmer}")
  private boolean programmer;
  @Value("#{injectSimpleConfig2.ageInSeconds}")
  private Long ageInSeconds;

  public static void main(String[] args) {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:spring/app-context-annotation.xml");
    context.refresh();
    final InjectSimpleSpel2 injectSimpleSpel = (InjectSimpleSpel2) context.getBean("injectSimpleSpel2");
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
