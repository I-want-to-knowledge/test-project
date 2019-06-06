package com.geo.source.spring_simple.example5_AOP.aop_framework.aop_namespace;

import com.geo.source.spring_simple.example5_AOP.pointcut.static_name_pointcut.Guitar;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 注解，通知
 *
 * @author YanZhen
 * @since 2019-06-06 14:45
 */
@Component
@Aspect
public class AnnotationAdvice {
  @Pointcut("execution(* sing(com.geo.source.spring_simple.example5_AOP.pointcut.static_name_pointcut.Guitar))" +
          " && args(value)")
  public void singExecution(Guitar value) {
  }

  @Pointcut("bean(grammy*)")
  public void isJohn() {
  }

  @Before(value = "singExecution(value) && isJohn()", argNames = "joinPoint,value")
  public void simpleBeforeAdvice(JoinPoint joinPoint, Guitar value) {
    if (value.getBrand().equals("Gibson")) {
      System.out.println("Execution: " + joinPoint.getSignature().getDeclaringTypeName()
              + " " + joinPoint.getSignature().getName());
    } else {
      System.out.println("是 " + value.getBrand() + " 牌子");
    }
  }

  @Around(value = "singExecution(value) && isJohn()", argNames = "proceedingJoinPoint,value")
  public Object simpleAroundAdvice(ProceedingJoinPoint proceedingJoinPoint, Guitar value) throws Throwable {
    System.out.println("Before Execution: " + proceedingJoinPoint.getSignature().getDeclaringTypeName()
            + " " + proceedingJoinPoint.getSignature().getName() + " argument: " + value.getBrand());
    final Object proceed = proceedingJoinPoint.proceed();
    System.out.println("After Execution: " + proceedingJoinPoint.getSignature().getDeclaringTypeName()
            + " " + proceedingJoinPoint.getSignature().getName() + " argument: " + value.getBrand());
    System.out.println();
    return proceed;
  }
}
