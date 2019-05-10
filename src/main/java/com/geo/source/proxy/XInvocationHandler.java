package com.geo.source.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理实现
 *
 * @author YanZhen
 * @since 2019-04-27 15:12
 */
public class XInvocationHandler<T> implements InvocationHandler {
  private T obj;

  XInvocationHandler(T obj) {
    this.obj = obj;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("动态代理登场！");
    final Object invoke = method.invoke(this.obj, args);
    System.out.println("动态代理结束！");
    return invoke;
  }

  public Object get(Class c) {
    return Proxy.newProxyInstance(c.getClassLoader(), new Class[]{c}, this);
  }
}
