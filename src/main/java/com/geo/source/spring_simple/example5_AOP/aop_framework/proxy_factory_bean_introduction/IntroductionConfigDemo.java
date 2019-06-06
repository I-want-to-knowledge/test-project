package com.geo.source.spring_simple.example5_AOP.aop_framework.proxy_factory_bean_introduction;

import com.geo.source.spring_simple.example5_AOP.introduction.Contact;
import com.geo.source.spring_simple.example5_AOP.introduction.IsModified;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * ProxyFactoryBean 的 Introduction 测试
 *
 * @author YanZhen
 * @since 2019-06-05 16:46
 */
public class IntroductionConfigDemo {
  public static void main(String[] args) {
    // m1();
    m2();
  }

  /**
   * 注解注入
   */
  private static void m2() {
    final GenericApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    final Contact contact = context.getBean("introductionBean1", Contact.class);
    final IsModified isModified = context.getBean("introductionBean1", IsModified.class);

    System.out.println("Is Contact?: " + (contact instanceof Contact));
    System.out.println("Is IsModified?: " + (isModified instanceof IsModified));
    System.out.println("Has been modified?: " + isModified.isModified());
    contact.setName("yz");
    System.out.println("Has been modified?: " + isModified.isModified());
    contact.setName("yy");
    System.out.println("Has been modified?: " + isModified.isModified());

    context.close();
  }

  /**
   * xml注入
   */
  private static void m1() {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("spring3/app-context-introduction.xml");
    context.refresh();

    final Contact contact = context.getBean("introductionBean", Contact.class);
    final IsModified isModified = context.getBean("introductionBean", IsModified.class);

    System.out.println("Is Contact?: " + (contact instanceof Contact));
    System.out.println("Is IsModified?: " + (isModified instanceof IsModified));
    System.out.println("Has been modified?: " + isModified.isModified());
    contact.setName("yz");
    System.out.println("Has been modified?: " + isModified.isModified());
    contact.setName("yy");
    System.out.println("Has been modified?: " + isModified.isModified());

    context.close();
  }
}
