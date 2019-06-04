package com.geo.source.spring_simple.example5_AOP.composable_pointcut;

import com.geo.source.spring_simple.example5_AOP.pointcut.static_name_pointcut.GrammyGuitarist;
import com.geo.source.spring_simple.example5_AOP.pointcut.static_name_pointcut.Guitar;
import com.geo.source.spring_simple.example5_AOP.pointcut.static_pointcut.SimpleAdvice;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcher;

import java.lang.reflect.Method;

/**
 * 组合切入点
 *
 * @author YanZhen
 * @since 2019-06-04 14:01
 */
public class ComposablePointcutExample {
  public static void main(String[] args) {
    final GrammyGuitarist guitarist = new GrammyGuitarist();
    final ComposablePointcut pointcut = new ComposablePointcut(ClassFilter.TRUE, new SingMethodMatcher());
    System.out.println("Test 1 >>");
    final GrammyGuitarist proxy = getProxy(pointcut, guitarist);
    testInvoke(proxy);
    System.out.println();

    System.out.println("Test 2 >>");
    // SingMethodMatcher 与 TalkMethodMatcher 的匹配结果求并集
    pointcut.union(new TalkMethodMatcher());
    final GrammyGuitarist proxy1 = getProxy(pointcut, guitarist);
    testInvoke(proxy1);
    System.out.println();

    System.out.println("Test 3 >>");
    // SingMethodMatcher 与 TalkMethodMatcher 的匹配结果求并集 与 RestMethodMatcher 的匹配结果求交集
    pointcut.intersection(new RestMethodMatcher());
    final GrammyGuitarist proxy2 = getProxy(pointcut, guitarist);
    testInvoke(proxy2);
    System.out.println();

    final ComposablePointcut pointcut4 = new ComposablePointcut(ClassFilter.TRUE, new SingMethodMatcher());
    System.out.println("Test 4 >>");
    // SingMethodMatcher与RestMethodMatcher与TalkMethodMatcher的匹配结果求并集集
    pointcut4.union(new RestMethodMatcher()).union(new TalkMethodMatcher());
    final GrammyGuitarist proxy3 = getProxy(pointcut4, guitarist);
    testInvoke(proxy3);
    System.out.println();

  }

  /**
   * 执行被代理的类的方法
   * @param proxy 被代理的类
   */
  private static void testInvoke(GrammyGuitarist proxy) {
    proxy.sing();
    proxy.sing(new Guitar());
    proxy.talk();
    proxy.rest();
  }

  /**
   * 创建切入点
   * @param pointcut 组合切入点
   * @param guitarist 被代理的类
   * @return 创建切入点，被代理的类
   */
  private static GrammyGuitarist getProxy(ComposablePointcut pointcut, GrammyGuitarist guitarist) {
    final DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleAdvice());
    final ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.addAdvisor(advisor);
    proxyFactory.setTarget(guitarist);
    return (GrammyGuitarist) proxyFactory.getProxy();
  }

  private static class SingMethodMatcher extends StaticMethodMatcher {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
      return method.getName().startsWith("si");
    }
  }
  private static class TalkMethodMatcher extends StaticMethodMatcher {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
      return "talk".equals(method.getName());
    }
  }
  private static class RestMethodMatcher extends StaticMethodMatcher {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
      return method.getName().endsWith("st");
    }
  }

}
