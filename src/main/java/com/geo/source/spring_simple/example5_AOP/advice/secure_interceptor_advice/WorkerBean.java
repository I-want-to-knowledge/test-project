package com.geo.source.spring_simple.example5_AOP.advice.secure_interceptor_advice;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 工人，被代理者
 *
 * @author YanZhen
 * @since 2019-05-30 12:54
 */
class WorkerBean {

  void doSomeWork(int noOfTimes) {
    IntStream.rangeClosed(0, noOfTimes).forEach(s->work());
  }

  private void work() {
    System.out.print("");
  }
}
