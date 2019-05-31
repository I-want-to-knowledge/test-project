package com.geo.source.spring_simple.example5_AOP.pointcut.annotation_pointcut;

import com.geo.source.spring_simple.example5_AOP.pointcut.static_name_pointcut.Guitar;
import com.geo.source.spring_simple.example5_AOP.pointcut.static_pointcut.SimpleAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

/**
 * 注解切入点测试
 *
 * @author YanZhen
 * @since 2019-05-31 15:16
 */
public class AnnotationPointcutDemo {
  public static void main(String[] args) {
    m1();
    m2();
  }

  /**
   * 方便版本
   */
  private static void m2() {
    //
  }

  /**
   * 原始版本
   */
  private static void m1() {
    final AnnotationMatchingPointcut pointcut = AnnotationMatchingPointcut.forMethodAnnotation(AdviceRequired.class);

    final DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleAdvice());

    final ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.addAdvisor(advisor);
    proxyFactory.setTarget(new Guitarist());

    final Guitarist proxy = (Guitarist) proxyFactory.getProxy();
    proxy.sing(new Guitar());
    proxy.sing();
  }
}
