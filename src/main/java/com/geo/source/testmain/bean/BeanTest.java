package com.geo.source.testmain.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * bean
 *
 * @author YanZhen
 * @since 2019-03-25 12:43
 */
public class BeanTest {

  public static void main(String[] args) {
    // getBean();
    getBeanFactory();
  }

  /**
   * 通过工厂注入
   */
  private static void getBeanFactory() {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    Abc abc = (Abc) ctx.getBean("p3", new Object[]{"王五", "10"});
    System.out.println(abc);
  }

  /**
   *
   */
  private static void getBean() {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    Abc abc = ctx.getBean("p2",Abc.class);
    System.out.println(abc);
  }
}
