package com.geo.source.spring_simple.example2_3_chapter.AutomaticAssembly;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 自动装配bean，以注解的形式
 *
 * @author YanZhen
 * @since 2019-05-21 14:30
 */
@Component
@Lazy // 调用的时候在init
public class Target3 {
  private Foo2 foo2One;
  private Foo2 foo2Two;
  private Bar bar;

  public static void main(String[] args) {
    final GenericApplicationContext context = new AnnotationConfigApplicationContext(Target3Configuration.class);
    final Target3 targetByType = context.getBean(Target3.class);
    context.close();
  }

  public Target3() {
  }

  public Target3(Foo2 foo2) {
    System.out.println("Target(Foo foo) called");
  }

  public Target3(Foo2 foo2, Bar bar) {
    System.out.println("Target(Foo foo, Bar bar) called");
  }

  @Autowired
  @Qualifier("foo2Impl1")//  与 @Primary 都是解决冲突
  public void setFoo2One(Foo2 foo2One) {
    this.foo2One = foo2One;
    System.out.println("Property fooOne set");
  }

  @Autowired
  @Qualifier("foo2Impl2")//  与 @Primary 都是解决冲突
  public void setFoo2Two(Foo2 foo2Two) {
    this.foo2Two = foo2Two;
    System.out.println("Property fooTwo set");
  }

  @Autowired
  public void setBar(Bar bar) {
    this.bar = bar;
    System.out.println("Property bar set");
  }
}
