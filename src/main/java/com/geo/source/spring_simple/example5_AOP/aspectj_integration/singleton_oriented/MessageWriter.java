package com.geo.source.spring_simple.example5_AOP.aspectj_integration.singleton_oriented;

/**
 * 单例切面，测试
 *
 * @author YanZhen
 * @since 2019-06-10 10:10
 */
class MessageWriter {
  void writerMessage() {
    System.out.println("foobar!");
  }

  void foo() {
    System.out.println("foo");
  }
}
