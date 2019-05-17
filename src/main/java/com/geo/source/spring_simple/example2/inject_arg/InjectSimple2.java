package com.geo.source.spring_simple.example2.inject_arg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * 参数注入测试
 *
 * @author YanZhen
 * @since 2019-05-16 16:40
 */
@Service
public class InjectSimple2 {
  @Value("yz")
  private String name;
  @Value("26")
  private int age;
  @Value("1.78")
  private float height;
  @Value("true")
  private boolean programmer;
  @Value("123456789")
  private Long ageInSeconds;

  public static void main(String[] args) {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:spring/app-context-annotation.xml");
    context.refresh();
    final InjectSimple2 injectSimple = (InjectSimple2) context.getBean("injectSimple2");
    System.out.println(injectSimple);
    context.close();
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
