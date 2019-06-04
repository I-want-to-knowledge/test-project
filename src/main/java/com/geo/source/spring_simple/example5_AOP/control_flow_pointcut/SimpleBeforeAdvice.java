package com.geo.source.spring_simple.example5_AOP.control_flow_pointcut;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * 控制流切入点，测试
 *
 * @author YanZhen
 * @since 2019-06-04 11:16
 */
public class SimpleBeforeAdvice implements MethodBeforeAdvice {

  @Override
  public void before(Method method, Object[] args, Object target) throws Throwable {
    System.out.println("Before method: " + method);
  }
}
