package com.geo.source.testmain.publictest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 数组测试
 *
 * @author YanZhen
 * 2018-11-19 17:34:05
 * ArrayTest
 */
public class ArrayTest {

  public static void main(String[] args) {
    // method1();
    m1();
//    m2();
//    m3();
//    m4();
  }

  /**
   * 数组引用首先被计算
   */
  private static void m1() {
    int[] a = {11, 12, 13, 14};
    int[] b = {0, 1, 2, 3};
    System.out.println("m1():" + a[(a = b)[3]]);
    System.out.println(Arrays.toString(a));
  }

  /**
   * 数组引用计算的猝然结束
   */
  private static void m2() {
    int index = 1;
    try {
      skedaddle(2)[index = 2]++;
    } catch (Exception e) {
      System.err.println("m2():" + e + ", index=" + index);
    }
  }

  /**
   * Null数组引用
   */
  private static void m3() {
    int index = 1;
    try {
      skedaddle(3)[index = 2]++;
    } catch (Exception e) {
      System.err.println("m3():" + e + ", index=" + index);
    }
  }

  /**
   * NullPointerException永远都不会发生，
   * 因为索引表达式必须在数组访问发生之前完成计算，
   * 而在数组访问中才包含对数组引用表达式的值是否为null进行检查；
   */
  private static void m4() {
    int[] a = null;
    try {
      int i = a[vamoose()];
      System.out.println("m4():" + i);
    } catch (Exception e) {
      System.err.println("m4():" + e);
    }
  }

  private static int vamoose() throws Exception {
    throw new Exception("Twenty-three skidoo!");
  }

  private static int[] skedaddle(int x) throws Exception {
    if (x == 3)
      return null;
    throw new Exception("Ciao");
  }

  // 测试set转array
  private static void method1() {
    Set<String> set = new HashSet<>();
    set.add("1");
    set.add("2");
    set.add("3");
    String[] array = set.toArray(new String[]{});
    for (String string : array) {
      System.out.println(string);
    }
  }

}
