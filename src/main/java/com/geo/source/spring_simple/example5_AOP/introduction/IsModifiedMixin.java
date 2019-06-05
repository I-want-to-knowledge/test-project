package com.geo.source.spring_simple.example5_AOP.introduction;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 授权引入拦截器
 *
 * @author YanZhen
 * @since 2019-06-04 17:47
 */
public class IsModifiedMixin extends DelegatingIntroductionInterceptor implements IsModified {
  private boolean isModified = false;// 是否被修改
  private final Map<Method, Method> methodCache = new HashMap<>();

  @Override
  public boolean isModified() {
    return isModified;
  }

  @Override
  public Object invoke(MethodInvocation mi) throws Throwable {
    if (!isModified) {
      if (mi.getMethod().getName().startsWith("set") && mi.getArguments().length == 1) {
        Method getter = getGetter(mi.getMethod());

        if (getter != null) {
          final Object o = mi.getArguments()[0];
          final Object o1 = getter.invoke(mi.getThis());
          if (o == null && o1 == null) {
            isModified = false;
          } else if (o != null && o1 == null) {
            isModified = true;
          } else {
            isModified = !o1.equals(o);
          }
        }
      }
    }
    return super.invoke(mi);
  }

  private Method getGetter(Method setter) {
    Method getter = methodCache.get(setter);
    if (getter != null) {
      return getter;
    }
    final String getterName = setter.getName().replaceFirst("set", "get");
    try {
      getter = setter.getDeclaringClass().getMethod(getterName);
      synchronized (methodCache) {
        methodCache.put(setter, getter);
      }
      return getter;
    } catch (NoSuchMethodException e) {
      System.err.println("异常！");
      return null;
    }
  }
}
