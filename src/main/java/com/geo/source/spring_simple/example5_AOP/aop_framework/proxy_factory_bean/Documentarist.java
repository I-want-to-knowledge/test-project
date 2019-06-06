package com.geo.source.spring_simple.example5_AOP.aop_framework.proxy_factory_bean;

import com.geo.source.spring_simple.example5_AOP.pointcut.static_name_pointcut.GrammyGuitarist;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 准备工作
 *
 * @author YanZhen
 * @since 2019-06-05 15:26
 */
public class Documentarist {
  protected GrammyGuitarist grammyGuitarist;
  public void execute() {
    grammyGuitarist.sing();
    grammyGuitarist.talk();
  }

  public void setGrammyGuitarist(GrammyGuitarist grammyGuitarist) {
    this.grammyGuitarist = grammyGuitarist;
  }
}
