package com.geo.source.testmain.interface_test.interface1;

/**
 * 类C
 * 1、类中的方法优先级最高，类和父类中声明的方法优先级高于任何声明为默认方法的优先级
 * 2、类中无任何声明，子接口的优先级更高，高于任何声明为默认方法的优先级
 * 3、以上不满足，继承了多接口的类必须通过显示覆盖和调用期望的方法，显式的选择哪种默认方法
 *
 * @author YanZhen
 * @since 2019-04-16 12:29
 */
public class C extends D implements A, B {
  public static void main(String[] args) {
    m1();
  }

  private static void m1() {
    new C().holler();
  }

  @Override
  public void holler() {
    B.super.holler();
  }
}
