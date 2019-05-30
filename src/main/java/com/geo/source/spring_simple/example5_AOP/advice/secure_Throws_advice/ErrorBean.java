package com.geo.source.spring_simple.example5_AOP.advice.secure_Throws_advice;

/**
 * 异常通知测试
 *
 * @author YanZhen
 * @since 2019-05-30 14:32
 */
public class ErrorBean {
  void ErrorProneMethod() throws Exception {
    throw new Exception("Generic Exception");
  }

  void otherErrorProneMethod() throws IllegalArgumentException {
    throw new IllegalArgumentException("IllegalArgument Exception");
  }
}
