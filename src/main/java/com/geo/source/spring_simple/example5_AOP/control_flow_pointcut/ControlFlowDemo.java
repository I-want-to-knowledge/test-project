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
  }

  private void test(TestBean proxy) {
    proxy.foo();
  }
}

class TestBean {
  void foo() {
    System.out.println("foo()");
  }
}