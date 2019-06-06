package com.geo.source.spring_simple.example5_AOP.aop_framework.aop_namespace;

import com.geo.source.spring_simple.example5_AOP.aop_framework.proxy_factory_bean.Documentarist;
import com.geo.source.spring_simple.example5_AOP.pointcut.static_name_pointcut.Guitar;
import org.springframework.stereotype.Component;

/**
 * aop命名空间，测试
 *
 * @author YanZhen
 * @since 2019-06-05 17:51
 */
public class NewDocumentarist extends Documentarist {
  @Override
  public void execute() {
    grammyGuitarist.sing();
    final Guitar guitar = new Guitar();
    grammyGuitarist.sing(guitar);
    guitar.setBrand("Gibson");
    grammyGuitarist.sing(guitar);
    grammyGuitarist.talk();
  }
}
