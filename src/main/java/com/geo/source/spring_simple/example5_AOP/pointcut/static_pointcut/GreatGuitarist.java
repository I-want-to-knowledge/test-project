package com.geo.source.spring_simple.example5_AOP.pointcut.static_pointcut;

import com.geo.source.spring_simple.example5_AOP.advice.simple_before_advice.Singer;

/**
 * 被代理对象
 *
 * @author YanZhen
 * @since 2019-05-30 17:36
 */
public class GreatGuitarist implements Singer {

  @Override
  public void sing() {
    System.out.println("I shot the sheriff, But I did not shoot the deputy!");
  }
}
