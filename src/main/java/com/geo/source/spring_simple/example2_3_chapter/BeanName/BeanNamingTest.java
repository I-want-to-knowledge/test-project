package com.geo.source.spring_simple.example2_3_chapter.BeanName;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * bean 命名，测试
 *
 * @author YanZhen
 * @since 2019-05-18 17:02
 */
public class BeanNamingTest {
  public static void main(String[] args) {
    // m1();
    m2();
  }

  private static void m2() {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:spring/app-context-bean-name-01.xml");
    context.refresh();

    final String s1 = context.getBean("john", String.class);
    final String s2 = context.getBean("jon", String.class);
    final String s3 = context.getBean("johnny", String.class);
    final String s4 = context.getBean("jonathan", String.class);
    final String s5 = context.getBean("jim", String.class);
    final String s6 = context.getBean("ion", String.class);

    System.out.println((s1 == s2));
    System.out.println((s2 == s3));
    System.out.println((s3 == s4));
    System.out.println((s4 == s5));
    System.out.println((s5 == s6));

    final Map<String, String> beans = context.getBeansOfType(String.class);
    if (beans.size() == 5) {
      System.out.println("There is only five String bean.");
    }
    System.out.println("id 和 name 的区分");
    beans.forEach((k, v) -> System.out.println("id : " + k + "\n aliases:"+ Arrays.toString(context.getAliases(k))));

    // 别名获取
    final String[] johns = context.getAliases("john");
    System.out.println("john names : " + Arrays.stream(johns).collect(Collectors.toList()));
    context.close();
  }

  /**
   * id name 什么都没有时 系统优先级
   */
  private static void m1() {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:spring/app-context-bean-name-01.xml");
    context.refresh();
    final Map<String, String> beans = context.getBeansOfType(String.class);
    beans.forEach((k, v) -> System.out.println(k));
    context.close();
  }
}
