package com.geo.source.testmain.recursion_iteration;

import com.geo.source.testmain.publictest.XxxUtils;

/**
 * 递归和迭代
 *
 * @author YanZhen
 * @since 2019-04-21 19:04
 */
public class RecursionAndIterationMain {

  public static void main(String[] args) {
    XxxUtils.start();
    System.out.println(RecursionTest.recursion1(10_000));
    XxxUtils.end();
    XxxUtils.start();
    System.out.println(RecursionTest.recursion2(1,10_000));
    XxxUtils.end();
  }

  /**
   * 递归的 省时 费时
   */
  private static class RecursionTest {

    /**
     * 使用栈帧方式的阶乘
     * @param n 数
     * @return 值
     */
    static long recursion1(long n) {
      return n == 1 ? 1 : n + recursion1(n - 1);
    }

    /**
     * 阶乘的尾递定义
     * @param acc 值
     * @param n 数
     * @return 值
     */
    static long recursion2(long acc, long n) {
      return n == 1 ? acc : recursion2(acc + n, n - 1);
    }
  }

  private class IterationTest {

  }
}
