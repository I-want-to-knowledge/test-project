package com.geo.source.spring_simple.example5_AOP.advice.secure_before_advice;

/**
 * 需要保护的类
 *
 * @author YanZhen
 * @since 2019-05-29 15:37
 */
public class SecureBean {

  void writeSecureMessage() {
    System.out.println("Every time I learn something new it pushes some old stuff out of my brain");
  }
}
