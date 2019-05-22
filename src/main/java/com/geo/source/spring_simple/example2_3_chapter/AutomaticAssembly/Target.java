package com.geo.source.spring_simple.example2_3_chapter.AutomaticAssembly;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 自动装配Bean，以xml的形式
 * app-context-xml.xml
 *
 * @author YanZhen
 * @since 2019-05-21 12:51
 */
public class Target {
  private Foo fooOne;
  private Foo fooTwo;
  private Bar bar;

  public static void main(String[] args) {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:spring/app-context-xml.xml");
    context.refresh();
    System.out.println("========targetName=========");
    final Target targetName = context.getBean("targetName", Target.class);
    System.out.println("========targetType=========");
    final Target targetType = context.getBean("targetType", Target.class);
    System.out.println("========targetConstructor=========");
    final Target targetConstructor = context.getBean("targetConstructor", Target.class);
    context.close();
  }

  public Target() {
  }

  public Target(Foo foo) {
    System.out.println("Target(Foo foo) called");
  }

  public Target(Foo foo, Bar bar) {
    System.out.println("Target(Foo foo, Bar bar) called");
  }

  public void setFooOne(Foo fooOne) {
    this.fooOne = fooOne;
    System.out.println("Property fooOne set");
  }

  public void setFooTwo(Foo fooTwo) {
    this.fooTwo = fooTwo;
    System.out.println("Property fooTwo set");
  }

  public void setBar(Bar bar) {
    this.bar = bar;
    System.out.println("Property bar set");
  }
}
