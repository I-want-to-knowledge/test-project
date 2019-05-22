package com.geo.source.spring_simple.example2_3_chapter.BeanExtends;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Bean 继承，测试
 * context-parent.xml
 *
 * @author YanZhen
 * @since 2019-05-21 16:14
 */
public class InheritanceDemo {
  public static void main(String[] args) {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:spring/context-parent.xml");
    context.refresh();
    final BeanExtends inheritanceParent = context.getBean("inheritanceParent", BeanExtends.class);
    final BeanExtends inheritanceChild = context.getBean("inheritanceChild", BeanExtends.class);
    System.out.println(inheritanceParent);
    System.out.println(inheritanceChild);
    context.close();
  }
}
