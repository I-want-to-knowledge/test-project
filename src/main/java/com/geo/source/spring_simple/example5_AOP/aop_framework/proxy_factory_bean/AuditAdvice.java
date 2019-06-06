package com.geo.source.spring_simple.example5_AOP.aop_framework.proxy_factory_bean;

import org.aopalliance.aop.Advice;
import org.aspectj.lang.JoinPoint;

/**
 * 通知
 *
 * @author YanZhen
 * @since 2019-06-05 15:32
 */
public class AuditAdvice {
  public void simpleBeforeAdvice(JoinPoint joinPoint) {
    System.out.println("Executing: " + joinPoint.getSignature().getDeclaringTypeName() + " "
            + joinPoint.getSignature().getName());
  }
}
