package com.geo.source.testmain.collector_test;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * 收集器测试
 *
 * @author YanZhen
 * @since 2019-04-11 11:26
 */
public class CollectorMain {

  public static void main(String[] args) {
    int n = 10_000;
    final Map<Boolean, List<Integer>> map = IntStream.rangeClosed(2, n).boxed().collect(new PrimeNumbersCollector());
    System.out.println(map.get(true));
  }
}
