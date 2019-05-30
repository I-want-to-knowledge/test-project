package com.geo.source.spring_simple.example5_AOP.advice.secure_interceptor_advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

import java.util.Arrays;

/**
 * 方法拦截器
 *
 * @author YanZhen
 * @since 2019-05-30 12:57
 */
public class ProfilingInterceptor implements MethodInterceptor {

  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start(invocation.getMethod().getName());
    final Object proceed = invocation.proceed();
    stopWatch.stop();
    dumpInfo(invocation, stopWatch.getTotalTimeMillis());
    return proceed;
  }

  private void dumpInfo(MethodInvocation invocation, long totalTimeMillis) {
    System.out.println("Executed method: " + invocation.getMethod().getName());
    System.out.println("On object of type: " + invocation.getThis().getClass().getName());
    System.out.println("With arguments:");
    final Object[] arguments = invocation.getArguments();
    Arrays.stream(arguments).forEach(s -> System.out.println(" > " + s));
    System.out.println();
    System.out.println("Took: " + totalTimeMillis + " ms");
  }
}
