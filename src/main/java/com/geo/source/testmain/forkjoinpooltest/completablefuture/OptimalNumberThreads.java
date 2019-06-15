package com.geo.source.testmain.forkjoinpooltest.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

/**
 * 自动调节最优线程数
 *
 * @author YanZhen
 * @since 2019-04-19 20:05
 */
class OptimalNumberThreads<T> {
  private final Executor executor;

  OptimalNumberThreads(int threadNum) {
    if (threadNum == 0) {
      threadNum = Runtime.getRuntime().availableProcessors();
    }
    this.executor = Executors.newFixedThreadPool(Math.min(threadNum, 100),// 线程数调优，上限是100
            r -> {
              final Thread thread = new Thread(r);
              thread.setDaemon(true);// 使用守护进程-不会阻塞程序的关停
              return thread;
            });
  }

  Executor getExecutor() {
    return executor;
  }

  /**
   * 异步处理
   * @param supplier 供应商
   * @param <U> 返回类型
   * @return CompletableFuture
   */
  public <U> CompletableFuture<U> completableAsync(Supplier<U> supplier) {
    return CompletableFuture.supplyAsync(supplier, executor);
  }
}
