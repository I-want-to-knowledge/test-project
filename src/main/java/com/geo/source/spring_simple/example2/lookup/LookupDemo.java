package com.geo.source.spring_simple.example2.lookup;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.StopWatch;

/**
 * 查找方法注入，测试
 *
 * @author YanZhen
 * @since 2019-05-18 13:44
 */
public class LookupDemo {

  @Configuration
  @ComponentScan("com.geo.source.spring_simple.example2.lookup")
  static class LookupConfig {}

  public static void main(String[] args) {
    m1();// 例1
    m2();// 例2
  }

  /**
   * 类注入
   */
  private static void m2() {
    final GenericApplicationContext context = new AnnotationConfigApplicationContext(LookupConfig.class);
    final DemoBean2 standardLookupDemoBean2 = context.getBean("standardLookupDemoBean2", DemoBean2.class);// 查找方法注入
    final DemoBean2 abstractLookupDemoBean2 = context.getBean("abstractLookupDemoBean2", DemoBean2.class);

    displayInfo2("abstractLookupDemoBean2", abstractLookupDemoBean2);// 查找方法注入
    displayInfo2("standardLookupDemoBean2", standardLookupDemoBean2);// DI注入

    context.close();
  }

  /**
   * xml文件注入
   */
  private static void m1() {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:spring/app-context-xml.xml");
    context.refresh();
    final DemoBean abstractLookupBean = context.getBean("abstractLookupBean", DemoBean.class);// 查找方法注入
    final DemoBean standardLookupBean = context.getBean("standardLookupBean", DemoBean.class);

    displayInfo("abstractLookupBean", abstractLookupBean);// 每次获取的Singer对象都不一样，查找方法注入
    displayInfo("standardLookupBean", standardLookupBean);// setter注入

    context.close();
  }

  private static void displayInfo(String s, DemoBean lookupBean) {
    final Singer singer1 = lookupBean.getMySinger();
    final Singer singer2 = lookupBean.getMySinger();
    System.out.println(s + ": Singer Instance the Same?" + (singer1 == singer2));
    StopWatch stopWatch = new StopWatch();
    stopWatch.start("lookupDemo");
    for (int i = 0; i < 100000; i++) {
      final Singer singer = lookupBean.getMySinger();
      singer.sing();
    }

    stopWatch.stop();
    System.out.println("100000 gets took " + stopWatch.getTotalTimeMillis() + " ms");
  }

  private static void displayInfo2(String s, DemoBean2 lookupBean2) {
    final Singer2 singer21 = lookupBean2.getMySinger2();
    final Singer2 singer22 = lookupBean2.getMySinger2();
    System.out.println(s + ": Singer Instance the Same?" + (singer21 == singer22));
    StopWatch stopWatch = new StopWatch();
    stopWatch.start("lookupDemo");
    for (int i = 0; i < 100000; i++) {
      final Singer2 singer2 = lookupBean2.getMySinger2();
      singer2.sing();
    }

    stopWatch.stop();
    System.out.println("100000 gets took " + stopWatch.getTotalTimeMillis() + " ms");
  }
}
