package com.geo.source.spring_simple.example5_AOP.advice.simple_before_advice;

/**
 * 吉他弹奏者
 *
 * @author YanZhen
 * @since 2019-05-29 14:20
 */
public class Guitarist implements Singer {
  private String lyric = "You're gonna live forever in me";

  @Override
  public void sing() {
    System.out.println(lyric);
  }
}
