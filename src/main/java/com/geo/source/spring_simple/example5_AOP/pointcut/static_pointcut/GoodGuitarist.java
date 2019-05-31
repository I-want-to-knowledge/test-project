package com.geo.source.spring_simple.example5_AOP.pointcut.static_pointcut;

import com.geo.source.spring_simple.example5_AOP.advice.simple_before_advice.Singer;

/**
 * 被代理对象
 *
 * @author YanZhen
 * @since 2019-05-30 17:33
 */
public class GoodGuitarist implements Singer {

  @Override
  public void sing() {
    System.out.println("Who says I can't be free From all of the things that I used to be");
  }
}
