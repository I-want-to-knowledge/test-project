package com.geo.source.spring_simple.example4_chapter.initMethod;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 初始化方法测试，创建Bean生命周期回调
 *
 * @author YanZhen
 * @since 2019-05-22 11:08
 */
public class InitMethodTest2 implements InitializingBean {
  private static final String DEFAULT_NAME = "Eric Clapton";

  private String name;
  private int age = Integer.MIN_VALUE;

  public static void main(String[] args) {
    // 在xml中使用default-lazy-init属性延迟加载bean
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:spring2/app-context-init.xml");
    context.refresh();
    getBean("init1", context);
    getBean("init2", context);
    getBean("init3", context);// 报bean创建异常
    context.close();
  }

  private static void getBean(String initStr, GenericXmlApplicationContext context) {
    final InitMethodTest2 initObj = context.getBean(initStr, InitMethodTest2.class);
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

  @Override
  public void afterPropertiesSet() {
    System.out.println("Initializing bean");

    if (name == null) {
      System.out.println("Using default name");
      name = DEFAULT_NAME;
    }

    if (age == Integer.MIN_VALUE) {
      throw new IllegalArgumentException("You must set the age property of any beans of type " + InitMethodTest2.class);
    }


  }
}
