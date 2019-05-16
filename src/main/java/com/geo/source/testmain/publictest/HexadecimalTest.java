package com.geo.source.testmain.publictest;

/**
 * byte
 *
 * @author YanZhen
 * @since 2019-05-15 16:39
 */
public class HexadecimalTest {
  public static void main(String[] args) {
    m1();
  }

  private static void m1() {
    final byte[] bytes = new byte[1];
    bytes[0] = (byte) 128;// 二进制：1111 1111
    System.out.println(bytes[0]);
  }
}
