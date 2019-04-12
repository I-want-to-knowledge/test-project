package com.geo.source.testmain.forkjoinpooltest;

import com.geo.source.testmain.publictest.XxxUtils;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

/**
 * 分支/合并框架
 *
 * @author YanZhen
 * @since 2019-04-11 16:37
 */
public class ForkJoinPoolMain {
  public static void main(String[] args) {
    XxxUtils.start();
    long n = 100_000_000;
    long[] longs = LongStream.rangeClosed(1, n).toArray();
    final ForkJoinSumCalculator forkJoinSumCalculator = new ForkJoinSumCalculator(longs);
    final Long invoke = new ForkJoinPool().invoke(forkJoinSumCalculator);
    System.out.println(invoke);
    XxxUtils.end();
  }
}
