package com.geo.source.spring_simple.example2_3_chapter.AutomaticAssembly;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 自动装配bean，以xml的形式
 * app-context-xml.xml
 *
 * @author YanZhen
 * @since 2019-05-21 14:30
 */
public class Target2 {
  private Foo2 foo2One;
  private Foo2 foo2Two;
  private Bar bar;

  public static void main(String[] args) {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:spring/app-context-xml.xml");
    context.refresh();
    final Target2 targetByType = context.getBean("targetByType", Target2.class);
    context.close();
  }

  public Target2() {
  }

  public Target2(Foo2 foo2) {
    System.out.println("Target(Foo foo) called");
  }

  public Target2(Foo2 foo2, Bar bar) {
    System.out.println("Target(Foo foo, Bar bar) called");
  }

  public void setFoo2One(Foo2 foo2One) {
    this.foo2One = foo2One;
    System.out.println("Property fooOne set");
  }

  public void setFoo2Two(Foo2 foo2Two) {
    this.foo2Two = foo2Two;
    System.out.println("Property fooTwo set");
  }

  public void setBar(Bar bar) {
    this.bar = bar;
    System.out.println("Property bar set");
  }
}
