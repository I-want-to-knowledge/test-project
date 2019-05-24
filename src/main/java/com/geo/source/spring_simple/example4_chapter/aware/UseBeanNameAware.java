package com.geo.source.spring_simple.example4_chapter.aware;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * BeanNameAware的使用
 *
 * @author YanZhen
 * @since 2019-05-23 14:29
 */
public class UseBeanNameAware {
  public static void main(String[] args) {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:spring2/app-context-aware.xml");
    context.refresh();
    final BeanNameAwareTest beanNameAwareTest = context.getBean("beanNameAwareTest", BeanNameAwareTest.class);
    System.out.println(beanNameAwareTest);
    context.close();
  }

  static class BeanNameAwareTest implements BeanNameAware {
    private String classBeanName;

    @Override
    public void setBeanName(String name) {
      this.classBeanName = name;
    }

    String getClassBeanName() {
      return classBeanName;
    }

    @Override
    public String toString() {
      return "Bean name " + classBeanName + " - toString()";
    }
  }
}
