package com.geo.source.spring_simple.example2_3_chapter.inject_arg;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 参数注入测试
 *
 * @author YanZhen
 * @since 2019-05-16 16:40
 */
public class InjectSimple {
  private String name;
  private int age;
  private float height;
  private boolean programmer;
  private Long ageInSeconds;

  public static void main(String[] args) {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:spring/app-context-xml-args.xml");
    context.refresh();
    final InjectSimple injectSimple = (InjectSimple) context.getBean("injectSimple");
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
    return "name:" + name + "\tage:" + age + "\theight:" + height
            + "\tprogrammer:" + programmer + "\tageInSeconds:" + ageInSeconds;
  }
}
