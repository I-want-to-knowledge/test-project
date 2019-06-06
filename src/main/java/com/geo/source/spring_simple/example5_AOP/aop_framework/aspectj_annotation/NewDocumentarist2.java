package com.geo.source.spring_simple.example5_AOP.aop_framework.aspectj_annotation;

import com.geo.source.spring_simple.example5_AOP.pointcut.static_name_pointcut.GrammyGuitarist;
import com.geo.source.spring_simple.example5_AOP.pointcut.static_name_pointcut.Guitar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * aop命名空间，测试
 *
 * @author YanZhen
 * @since 2019-06-05 17:51
 */
@Component
public class NewDocumentarist2 {
  private GrammyGuitarist grammyGuitarist;
  public void execute() {
    grammyGuitarist.sing();
    final Guitar guitar = new Guitar();
    grammyGuitarist.sing(guitar);
    guitar.setBrand("Gibson");
    grammyGuitarist.sing(guitar);
    grammyGuitarist.talk();
  }

  @Autowired
  public void setGrammyGuitarist(GrammyGuitarist grammyGuitarist) {
    this.grammyGuitarist = grammyGuitarist;
  }
}
