package com.geo.source.testmain.collector_test;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

/**
 * 质数收集器
 *
 * @author YanZhen
 * @since 2019-04-11 09:59
 */
public class PrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {
  @Override
  public Supplier<Map<Boolean, List<Integer>>> supplier() {
    return () -> new HashMap<Boolean, List<Integer>>() {{
      put(true, new ArrayList<>());
      put(false, new ArrayList<>());
    }};
  }

  @Override
  public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
    return (booleanListMap, integer) -> booleanListMap.get(isPrime(booleanListMap.get(true), integer)).add(integer);
  }

  /**
   * 判断是否为质数
   * @param integers 质数组
   * @param integer 自然数
   * @return 是否质数
   */
  private Boolean isPrime(List<Integer> integers, Integer integer) {
    return takeWhile(integers, i -> i <= (int) Math.sqrt((double) integer)).stream().noneMatch(i -> integer % i == 0);
  }

  @Override
  public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
    // 该操作不能调用该方法
    // 并行计算时需要拆分数据进行计算，计算质数不能用并行
    return (map1, map2) -> {
      map1.get(true).addAll(map2.get(true));// 质数
      map1.get(false).addAll(map2.get(false));// 非质数
      return map1;
    };
  }

  @Override
  public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
    return Function.identity();
  }

  @Override
  public Set<Characteristics> characteristics() {
    return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
  }

  /**
   * 筛选符合条件的项目
   * @param list 所有项目
   * @param p 条件
   * @param <A> 类型
   * @return 符合条件的项目
   */
  private static <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
    // 遍历
    for (int i = 0; i < list.size(); i++) {
      // 如果不符合条件
      if (!p.test(list.get(i))) {
        // 返回之前的数据
        return list.subList(0, i);
      }
    }
    // 都符合条件的情况
    return list;
  }
}
