package com.geo.source.spring_simple.example5_AOP.pointcut.annotation_pointcut;

import com.geo.source.spring_simple.example5_AOP.advice.simple_before_advice.Singer;
import com.geo.source.spring_simple.example5_AOP.pointcut.static_name_pointcut.Guitar;

/**
 * 注解切入点，测试
 *
 * @author YanZhen
 * @since 2019-05-31 15:10
 */
public class Guitarist implements Singer {

  @Override
  public void sing() {
    System.out.println("zzz");
  }

  @AdviceRequired
  void sing(Guitar guitar) {
    System.out.println("play: " + guitar.play());
  }
}
