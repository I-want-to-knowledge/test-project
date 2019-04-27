package com.geo.source.lazy_stream;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 延迟列表
 *
 * @author YanZhen
 * @since 2019-04-23 14:58
 */
public class LazyList<T> implements MyList<T> {
  final T head;
  final Supplier<MyList<T>> tail;

  public LazyList(T head, Supplier<MyList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }

  @Override
  public T head() {
    return head;
  }

  @Override
  public MyList<T> tail() {
    return tail.get();
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public MyList<T> filter(Predicate<T> predicate) {
    return isEmpty() ? this : predicate.test(head())
            ? new LazyList<>(head(), () -> tail().filter(predicate)) : tail().filter(predicate);
  }

  public static void main(String[] args) {
    // lazyTest();
    primeNumber();
  }

  /**
   * 求质数
   */
  private static void primeNumber() {
    final LazyList<Integer> from = from(2);
    final Integer two = prime(from).head();
    final Integer three = prime(from).tail().head();
    final Integer five = prime(from).tail().tail().head();
    final Integer seven = prime(from).tail().tail().tail().head();
    System.out.println(two + " " + three + " " + five + " " + seven);
  }

  private static MyList<Integer> prime(MyList<Integer> num) {

    return new LazyList<>(num.head(), () -> prime(num.tail().filter(n -> n % num.head() != 0)));
  }

  private static void lazyTest() {
    LazyList<Integer> num = from(2);
    final Integer two = num.head();
    final Integer three = num.tail().head();
    final Integer four = num.tail().tail().head();
    System.out.println(two + " " + three + " " + four);
  }

  private static LazyList<Integer> from(int i) {
    return new LazyList<>(i, () -> from(i + 1));
  }
}
