package com.geo.source.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 动态代理测试
 *
 * @author YanZhen
 * @since 2019-04-27 15:16
 */
public class XMain {
  public static void main(String[] args) {
    final X x = (X) new XInvocationHandler(new XImpl()).get(X.class);
    x.a();
    x.b();
  }
}
