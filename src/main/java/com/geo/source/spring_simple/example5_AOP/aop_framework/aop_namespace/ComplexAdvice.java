package com.geo.source.spring_simple.example5_AOP.aop_framework.aop_namespace;

import com.geo.source.spring_simple.example5_AOP.pointcut.static_name_pointcut.Guitar;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 命名空间升级版
 *
 * @author YanZhen
 * @since 2019-06-06 10:10
 */
public class ComplexAdvice {

  public void simpleBeforeAdvice(JoinPoint joinPoint, Guitar value) {
    if (value.getBrand().equals("Gibson")) {
      System.out.println("Executing: " + joinPoint.getSignature().getDeclaringTypeName()
              + " " + joinPoint.getSignature().getName());
    } else {
      System.out.println("是 " + value.getBrand() + " 牌子的！");
    }
  }

  /**
   * 环绕通知
   * @param proceedingJoinPoint 进行链接点
   * @param value 附带参数值
   * @return 目标方法返回值
   * @throws Throwable 异常
   */
  public Object simpleAroundAdvice(ProceedingJoinPoint proceedingJoinPoint, Guitar value) throws Throwable {
    System.out.println("Before execution: " + proceedingJoinPoint.getSignature().getDeclaringTypeName()
            + " " + proceedingJoinPoint.getSignature().getName() + " argument: " + value.getBrand());
    final Object proceed = proceedingJoinPoint.proceed();
    System.out.println("After execution: " + proceedingJoinPoint.getSignature().getDeclaringTypeName()
            + " " + proceedingJoinPoint.getSignature().getName() + "argument: " + value.getBrand());
    System.out.println();
    return proceed;
  }
}
