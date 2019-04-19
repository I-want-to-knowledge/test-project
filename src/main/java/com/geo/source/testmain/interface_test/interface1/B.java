package com.geo.source.testmain.interface_test.interface1;

/**
 * 接口B
 *
 * @author YanZhen
 * @since 2019-04-16 12:28
 */
public interface B extends A {
  default void holler() {
    System.out.println("Interface B");
  }
}
