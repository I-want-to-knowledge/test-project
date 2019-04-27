package com.geo.source.lazy_stream;

import java.util.function.Predicate;

/**
 * list
 *
 * @author YanZhen
 * @since 2019-04-23 14:57
 */
interface MyList<T> {
  T head();
  MyList<T> tail();
  default boolean isEmpty() {
    return true;
  }

  MyList<T> filter(Predicate<T> predicate);
}
