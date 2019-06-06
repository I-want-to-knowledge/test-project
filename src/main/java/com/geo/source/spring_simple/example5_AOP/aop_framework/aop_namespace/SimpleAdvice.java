package com.geo.source.spring_simple.example5_AOP.aop_framework.aop_namespace;

import org.aspectj.lang.JoinPoint;

/**
 * 通知
 *
 * @author YanZhen
 * @since 2019-06-05 17:53
 */
public class SimpleAdvice {

  public void simpleBeforeAdvice(JoinPoint joinPoint) {
    System.out.println();
    System.out.println("Executing: " + joinPoint.getSignature().getDeclaringTypeName() + " "
            + joinPoint.getSignature().getName());
  }
}
