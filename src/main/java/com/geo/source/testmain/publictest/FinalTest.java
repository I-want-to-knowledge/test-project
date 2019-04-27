package com.geo.source.testmain.publictest;

/**
 * finalTest
 *
 * @author YanZhen
 * @since 2019-04-21 15:49
 */
public class FinalTest {
  public static void main(String[] args) {
    final A a = new A(1);
    System.out.println(a.getX());
    m1(a);
    System.out.println(a.getX());

    String a1 = "hello2";
    final String b = "hello";
    String d = "hello";
    String c = b + 2;// final修饰值为常量,直接替换
    String e = d + 2;// 通过链接来进行拼接
    System.out.println((a1 == c));// == 比较指针信息，链接信息
    System.out.println((a1 == e));
  }

  private static void m1(A a) {
    a = new A(4);
  }

  private static final class A {
    private final int x;

    A(int x) {
      this.x = x;
    }

    int getX() {
      return x;
    }

    /*void setX(int x) {
      this.x = x;
    }*/
  }
}
