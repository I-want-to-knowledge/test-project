package com.geo.source.testmain.publictest;

/**
 * 正则表达式
 *
 * @author YanZhen
 * @since 2019-05-22 15:18
 */
public class EL {
  public static void main(String[] args) {
    String a = "abc";
    System.out.println(a.matches("\\w{3}"));
  }
}
