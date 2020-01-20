package com.geo.source.proxy;

/**
 * 动态代理测试
 *
 * @author YanZhen
 * @since 2019-04-27 15:16
 */
public class XMain {
  public static void main(String[] args) {
    m1();
    m2();
  }

  /**
   * 动态代理v2版
   */
  private static void m2() {
    final X x = new XInvocationHandler().setT(new XImpl(), X.class);
    x.a();
    x.b();
  }

  /**
   * 动态代理
   */
  private static void m1() {
    final X x = (X) new XInvocationHandler(new XImpl()).newProxy(X.class);
    x.a();
    x.b();
  }
}
