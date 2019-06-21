package com.geo.source.testmain.publictest;

/**
 * 字节 测试
 *
 * @author YanZhen
 * @since 2019-06-14 09:50
 */
public class ByteTest {
  public static void main(String[] args) {
    // m1();
    // m2();
    m3();
  }

  private static void m3() {
    System.out.println(4096 & 4095);
    // System.out.println(-1L^(-1L << 5));
    System.out.println(~(-1L << 5));// -1L^(-1L << 5) 和 ~(-1L << 5) 等价
  }

  private static void m2() {
    System.out.println(~(-1 << 5));
    System.out.println(-1 << 5);
    System.out.println(1 << 5);
  }

  private static void m1() {
    byte b = (byte) 0xff;
    System.out.println(b);
  }
}
