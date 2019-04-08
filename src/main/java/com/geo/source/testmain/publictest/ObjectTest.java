package com.geo.source.testmain.publictest;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.*;

/**
 * 对象
 * 基本数据类型和普通对象
 *
 * @author YanZhen
 * @since 2019-04-01 13:30
 */
public class ObjectTest {
  public static void main(String[] args) {
    // m();
    m4();
    int a = 1;
    Integer b = null;
    out.println(a == b);
  }

  private static void m4() {
    final int a = 1;
    Runnable o = () -> out.println(a);
    // a = 2;
  }

  private static void m() {
    Integer integer = new Integer(10);
    m1(integer);
    out.println("2修改后=" + integer);

    String a = "";
    m2(a);
    out.println("2:" + a);

    Map<String, String> map = new HashMap<>();
    map.put("1a", "1a");
    m3(map);
    out.println("map2=" + map);
  }

  private static void m3(Map<String, String> map) {
    map.clear();
    map.put("2a", "2a");
    out.println("map1=" + map);
  }

  private static void m2(String a) {
    a = "123a";
    out.println("1:" + a);
  }

  private static void m1(Integer integer) {
    integer = 100;
    out.println("1修改后=" + integer);
  }
}
