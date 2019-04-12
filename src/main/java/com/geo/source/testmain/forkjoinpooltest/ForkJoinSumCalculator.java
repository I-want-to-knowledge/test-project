package com.geo.source.testmain.forkjoinpooltest;

import java.util.concurrent.RecursiveTask;

/**
 * 拆分合并算法
 *
 * @author YanZhen
 * @since 2019-04-11 16:18
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {
  private final long[] numbers;
  private final int start;
  private final int end;

  private static final long THRESHOLD = 10_000;

  ForkJoinSumCalculator(long[] numbers) {
    this(numbers, 0, numbers.length);
  }

  private ForkJoinSumCalculator(long[] numbers, int start, int end) {
    this.numbers = numbers;
    this.start = start;
    this.end = end;
    System.out.println(Thread.currentThread().getName());
  }

  @Override
  protected Long compute() {
    int length = end - start;
    // 小于指定数量，不再继续划分
    if (length < THRESHOLD) {
      return computeSequentially();
    }

    // 项目数量大于指定数量，继续拆分
    // 指定前一半数据
    final ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
    leftTask.fork();
    // 后一半数据本线程执行
    final ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
    final Long rightResult = rightTask.compute();
    final Long leftResult = leftTask.join();

    return rightResult + leftResult;
  }

  private Long computeSequentially() {
    long sum = 0;
    // start默认为0
    for (int i = start; i < end; i++) {
      sum += numbers[i];
    }
    return sum;
  }
}
