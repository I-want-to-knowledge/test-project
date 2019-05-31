package com.geo.source.spring_simple.example5_AOP.pointcut.aspectj_pointcut;

import com.geo.source.spring_simple.example5_AOP.pointcut.static_pointcut.SimpleAdvice;
import com.geo.source.spring_simple.example5_AOP.pointcut.static_regexp_pointcut.Guitarist;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * aspectJ切入点表达式
 *
 * @author YanZhen
 * @since 2019-05-31 14:28
 */
public class AspectJExpPointcutDemo {
  public static void main(String[] args) {
    m1();
    m2();
  }

  /**
   * 便捷版
   */
  private static void m2() {
    final AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
    advisor.setAdvice(new SimpleAdvice());
    advisor.setExpression("execution(* sing*(..))");

    commonality(advisor);
  }

  private static void commonality(PointcutAdvisor advisor) {
    final ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.addAdvisor(advisor);
    proxyFactory.setTarget(new Guitarist());
    final Guitarist proxy = (Guitarist) proxyFactory.getProxy();
    proxy.sing3();
    proxy.sing();
    proxy.rest();
  }

  /**
   * 原始版本
   */
  private static void m1() {
    final AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    pointcut.setExpression("execution(* sing*(..))");

    final DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleAdvice());

    commonality(advisor);
  }
}
