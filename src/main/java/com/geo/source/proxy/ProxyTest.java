package com.geo.source.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 简单代理
 *
 * @author YanZhen
 * @since 2019-05-29 09:42
 */
public class ProxyTest {
  public static void main(String[] args) {
    final X o = (X) Proxy.newProxyInstance(X.class.getClassLoader(), new Class[]{X.class}, new XIH<X>(new XImpl()));
    o.a();
    o.b();
  }

  static class XIH<T> implements InvocationHandler {
    private T t;

    XIH(T t) {
      this.t = t;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      System.out.println("通知开始！");
      final Object o = method.invoke(this.t, args);
      System.out.println("通知结束！");
      return o;
    }
  }
}
