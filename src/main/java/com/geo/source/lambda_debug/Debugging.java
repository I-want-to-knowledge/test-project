package com.geo.source.lambda_debug;

import java.util.Arrays;
import java.util.List;

/**
 * lambda表达式的调试
 * 栈跟踪
 *
 * @author YanZhen
 * @since 2019-04-13 16:04
 */
public class Debugging {

  public static void main(String[] args) {
    // m1();
    m2();
  }

  private static void m2() {
    final List<Integer> integers = Arrays.asList(2, 3, 4, 5);
    integers.stream()
            .peek(x -> System.out.println("debug1:" + x))// 窥视
            .map(x -> x + 17)
            .peek(x -> System.out.println("debug2:" + x))// 窥视
            .filter(x -> x % 0 == 0)
            .peek(x -> System.out.println("debug3:" + x))// 窥视
            .limit(3)
            .peek(x -> System.out.println("debug4:" + x))// 窥视
            .forEach(System.out::println);
  }

  private static void m1() {
    int[] a = new int[]{1};
    System.out.println(a[2]);
  }
}
