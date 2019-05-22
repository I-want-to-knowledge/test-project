package com.geo.source.spring_simple.example4_chapter.initMethod;

import org.springframework.context.support.GenericXmlApplicationContext;

import javax.annotation.PostConstruct;

/**
 * 初始化方法测试，创建Bean生命周期回调
 *
 * @author YanZhen
 * @since 2019-05-22 11:08
 */
public class InitMethodTest3 {
  private static final String DEFAULT_NAME = "Eric Clapton";

  private String name;
  private int age = Integer.MIN_VALUE;

  public static void main(String[] args) {
    // 在xml中使用default-lazy-init属性延迟加载bean
    // 利用JSR-250
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:spring2/app-context-init.xml");
    context.refresh();
    getBean("initA", context);
    getBean("initB", context);
    getBean("initC", context);// 报bean创建异常
    context.close();
  }

  private static void getBean(String initStr, GenericXmlApplicationContext context) {
    final InitMethodTest3 initObj = context.getBean(initStr, InitMethodTest3.class);
    System.out.println(initObj);
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "name : " + name + "\nage : " + age;
  }

  /**
   * Spring IoC 通过反射调用
   */
  @PostConstruct
  private void init() {
    System.out.println("Initializing bean");

    if (name == null) {
      System.out.println("Using default name");
      name = DEFAULT_NAME;
    }

    if (age == Integer.MIN_VALUE) {
      throw new IllegalArgumentException("You must set the age property of any beans of type " + InitMethodTest3.class);
    }


  }
}
