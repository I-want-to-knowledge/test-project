package com.geo.source.spring_simple.example5_AOP.control_flow_pointcut;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * 控制流切入点，测试
 *
 * @author YanZhen
 * @since 2019-06-04 11:19
 */
public class ControlFlowDemo {
  public static void main(String[] args) {
    final ControlFlowDemo demo = new ControlFlowDemo();
    demo.run();
  }

  private void run() {
    // 创建顾问，控制流切入点，在ControlFlowDemo类中的test方法执行通知
    // 为ControlFlowDemo类的test方法创建一个ControlFlowPointcut实例
    final DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(
            new ControlFlowPointcut(ControlFlowDemo.class, "test"), new SimpleBeforeAdvice());

    final ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.addAdvisor(advisor);
    proxyFactory.setTarget(new TestBean());

    final TestBean proxy = (TestBean) proxyFactory.getProxy();
    System.out.println("\tTrying normal invoke");
    proxy.foo();
    System.out.println("\n\tTrying under ControlFlowDemo.test()");
    test(proxy);

    System.out.println("最后一次调用：");
    proxy.foo();
  }

  private void test(TestBean proxy) {
    proxy.foo();
    System.out.println("\n\tTrying under ControlFlowDemo.test().test2()");
    test2(proxy);
  }

  private void test2(TestBean proxy) {
    proxy.foo();
  }
}

class TestBean {
  void foo() {
    System.out.println("foo()");
  }
}