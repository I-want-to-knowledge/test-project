package com.geo.source.spring_simple.example5_AOP.pointcut.static_regexp_pointcut;

import com.geo.source.spring_simple.example5_AOP.advice.simple_before_advice.Singer;

/**
 * 吉他手
 *
 * @author YanZhen
 * @since 2019-05-31 11:27
 */
public class Guitarist implements Singer {

  @Override
  public void sing() {
    System.out.println("Just keep me where the light is (1)");
  }

  void sing2() {
    System.out.println("Just keep me where the light is (2)");
  }

  public void sing3() {
    System.out.println("Just keep me where the light is (3)");
  }

  public void rest() {
    System.out.println("zzz");
  }
}
