package com.geo.source.spring_simple.example4_chapter.initMethod;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 初始化配置
 * 利用Bean的initMethod属性创建初始化回调方法
 *
 * @author YanZhen
 * @since 2019-05-22 17:57
 */
@Configuration
public class InitConfigurationDemo {

  @Lazy// 使用时再加载
  @Bean(initMethod = "init")
  Singer singer1() {
    final Singer singer = new Singer();
    singer.setAge(27);
    singer.setName("yz");
    return singer;
  }

  @Lazy// 使用时再加载
  @Bean(initMethod = "init")
  Singer singer2() {
    final Singer singer = new Singer();
    singer.setAge(26);
    return singer;
  }

  @Lazy// 使用时再加载
  @Bean(initMethod = "init")
  Singer singer3() {
    final Singer singer = new Singer();
    singer.setName("y");
    return singer;
  }

  public static void main(String[] args) {
    final GenericApplicationContext context = new AnnotationConfigApplicationContext(InitConfigurationDemo.class);
    getBean(context, "singer1");
    getBean(context, "singer2");
    getBean(context, "singer3");// bean创建异常
  }

  private static void getBean(GenericApplicationContext context, String singer) {
    final Singer s = context.getBean(singer, Singer.class);
    System.out.println(s);
  }

  private class Singer {
    private static final String DEFAULT_NAME = "Eric Clapton";

    private String name;
    private int age = Integer.MIN_VALUE;

    public void setName(String name) {
      this.name = name;
    }

    public void setAge(int age) {
      this.age = age;
    }

    /**
     * Spring IoC通过反射调用
     */
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

    @Override
    public String toString() {
      return "name : " + name + "\nage : " + age;
    }
  }
}
