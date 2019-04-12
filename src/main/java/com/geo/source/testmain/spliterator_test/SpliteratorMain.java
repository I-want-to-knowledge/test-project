package com.geo.source.testmain.spliterator_test;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 可分迭代器测试
 *
 * @author YanZhen
 * @since 2019-04-12 11:11
 */
public class SpliteratorMain {
  public static void main(String[] args) {
    String n = "Our greatest accomplishments cannot be behind us, because our destiny lies above us.";
    m1(n);// 误用并行流时
    m2(n);// 优化并行流
  }

  /**
   * 优化线程后
   * @param n 字符串
   */
  private static void m2(String n) {
    final Stream<Character> stream = StreamSupport.stream(new WordCounterSpliterator(n), true);// 并行流
    final WordCounter reduce = stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
    System.out.println("优化后的并行流计算单词的个数：" + reduce.getCounter());
  }

  /**
   * 当用并行流，随机拆分字符串时会出现错误
   * @param n 字符串
   */
  private static void m1(String n) {
    final Stream<Character> characterStream = IntStream.range(0, n.length()).mapToObj(n::charAt);
    final WordCounter reduce = characterStream.reduce(new WordCounter(0, true),
            WordCounter::accumulate, WordCounter::combine);
    System.out.println("顺序流计算单词的个数：" + reduce.getCounter());

    final Stream<Character> characterStream1 = IntStream.range(0, n.length()).mapToObj(n::charAt);
    final WordCounter reduce1 = characterStream1.parallel().reduce(new WordCounter(0, true),
            WordCounter::accumulate, WordCounter::combine);
    // 并行流拆分字符串时，会拆分其中的单词，导致单词数变多，得到错误的结果
    System.out.println("并行流计算单词的个数：" + reduce1.getCounter());
  }
}
