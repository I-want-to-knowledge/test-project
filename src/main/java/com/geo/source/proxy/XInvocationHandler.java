package com.geo.source.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理实现
 *
 * @author YanZhen
 * @since 2019-04-27 15:12
 */
public class XInvocationHandler implements InvocationHandler {
  private Object obj;

  public XInvocationHandler() {
  }

  <T> T setT(T t, Class<T> clazz) {
    this.obj = t;

    return this.newProxy(clazz);
  }

  XInvocationHandler(Object obj) {
    this.obj = obj;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
    System.out.println("动态代理登场！");
    final Object invoke = method.invoke(this.obj, args);
    System.out.println("动态代理结束！");
    return invoke;
  }

  public <T> T newProxy(Class<T> clazz) {
    final Object o = Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    return clazz.cast(o);
  }
}
