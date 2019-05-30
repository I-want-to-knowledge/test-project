package com.geo.source.spring_simple.example5_AOP.advice.secure_after_advice;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

import static com.geo.source.spring_simple.example5_AOP.advice.secure_after_advice.KeyGenerator.WEAK_KEY;

/**
 * 方法返回后通知
 *
 * @author YanZhen
 * @since 2019-05-29 17:50
 */
public class WeakKeyCheckAdvice implements AfterReturningAdvice {

  @Override
  public void afterReturning(Object returnValue, Method method, Object[] args, Object target) {
    if (target instanceof KeyGenerator) {
      final long l = (long) returnValue;
      if (l == WEAK_KEY) {
        throw new SecurityException("Key Generator generated a weak key. Try again");
      }
    }
  }
}
