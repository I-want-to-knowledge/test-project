package com.geo.source.testmain.forkjoinpooltest.completablefuture;

import com.geo.source.testmain.publictest.XxxUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * 线程数测试
 *
 * @author YanZhen
 * @since 2019-04-19 20:29
 */
public class ThreadNumMain {
  public static void main(String[] args) {
    final List<String> list = new ArrayList<>();
    list.add("A stock");
    list.add("B stock");
    list.add("C stock");
    list.add("D stock");
    list.add("E stock");
    list.add("F stock");
    list.add("G stock");
    list.add("H stock");

    threadNum(list);
    parallel(list);
  }

  private static void parallel(List<String> list) {
    XxxUtils.start();
    final List<String> stringList = list.stream().parallel()
            .map(ThreadNumMain::m1)
            .map(ThreadNumMain::m2).parallel()
            .map(s -> m1(s + " map2")).collect(Collectors.toList());
    XxxUtils.end();
    System.out.println("method1 : " + stringList);
  }

  private static void threadNum(List<String> list) {
    XxxUtils.start();
    final OptimalNumberThreads<String> executor = new OptimalNumberThreads<>(list.size());
    final List<CompletableFuture<String>> completableFutures = list.stream()
            .map(s -> executor.completableAsync(() -> m1(s)))
            .map(future -> future.thenApply(ThreadNumMain::m2))
            .map(future -> future.thenCompose(s ->
                    executor.completableAsync(() -> m1(s + " map2"))))
            .collect(Collectors.toList());
    final List<String> stringList = completableFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    XxxUtils.end();
    System.out.println("method2 : " + stringList);
  }

  private static String m1(String stock) {
    // System.out.println(stock + ",睡一秒再说！");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return stock;
  }

  private static String m2(String stock) {
    // System.out.println("m2 : " + stock);
    return stock + " m2";
  }
}
