package com.geo.source.testmain.publictest;

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
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");


    Abc abc = ctx.getBean("p2",Abc.class);


    System.out.println(abc);
  }
}
