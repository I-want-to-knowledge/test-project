package com.geo.source.spring_simple.example2_3_chapter.LookupMethodReplacer;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

/**
 * spring方法替换，测试！
 * 要替换的方法
 *
 * @author YanZhen
 * @since 2019-05-18 15:52
 */
public class FormatMessageReplacer implements MethodReplacer {

  @Override
  public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
    if (isFormatMessageMethod(method)) {
      final String msg = (String) args[0];
      return "<h2>" + msg + "</h2>";
    } else {
      throw new IllegalArgumentException("Unable to reimplement method" + method.getName());
    }
  }

  /**
   * 判断方法类型是否匹配
   * @param method 方法信息
   * @return 是否匹配
   */
  private boolean isFormatMessageMethod(Method method) {
    if (method.getParameterTypes().length != 1)
      return false;
    if (!("formatMessage".equals(method.getName())))
      return false;
    if (method.getReturnType() != String.class)
      return false;
    return method.getParameterTypes()[0] == String.class;
  }
}
