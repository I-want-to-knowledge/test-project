package com.geo.source.spring_simple.example5_AOP.pointcut.static_name_pointcut;

import com.geo.source.spring_simple.example5_AOP.advice.simple_before_advice.Singer;
import org.springframework.stereotype.Component;

/**
 * 格莱美吉他手奖
 *
 * @author YanZhen
 * @since 2019-05-31 10:53
 */
@Component
public class GrammyGuitarist implements Singer {

  @Override
  public void sing() {
    System.out.println("sing: Gravity is working against me And gravity wants to bring me down");
  }

  public void sing(Guitar guitar) {
    System.out.println("play: " + guitar.play());
  }

  public void rest() {
    System.out.println("zzz");
  }

  public void talk() {
    System.out.println("talk");
  }
}
