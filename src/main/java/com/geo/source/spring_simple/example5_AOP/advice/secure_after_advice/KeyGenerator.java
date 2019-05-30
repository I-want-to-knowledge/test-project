package com.geo.source.spring_simple.example5_AOP.advice.secure_after_advice;

import java.util.Random;

/**
 * 钥匙生产
 *
 * @author YanZhen
 * @since 2019-05-29 17:46
 */
class KeyGenerator {
  static final long WEAK_KEY = 0xFFFFFFF0000000L;
  private static final long STRONG_KEY = 0xACDF03F590AE56L;

  long getKey() {
    final int i = new Random().nextInt(3);
    if (i == 1) {
      return WEAK_KEY;
    }
    return STRONG_KEY;
  }
}
