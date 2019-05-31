package com.geo.source.spring_simple.example5_AOP.pointcut.dynamic_pointcut;

/**
 * 动态切入点测试
 *
 * @author YanZhen
 * @since 2019-05-31 09:47
 */
public class SampleBean {

  void foo(int x) {
    System.out.println("Invoked foo() with: " + x);
  }

  void bar() {
    System.out.println("Invoked bar()");
  }
}
