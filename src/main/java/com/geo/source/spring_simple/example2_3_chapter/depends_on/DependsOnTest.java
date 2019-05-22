package com.geo.source.spring_simple.example2_3_chapter.depends_on;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 解析依赖项
 *
 * @author YanZhen
 * @since 2019-05-20 17:28
 */
public class DependsOnTest implements ApplicationContextAware {
  ApplicationContext context;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.context = applicationContext;
  }

  private Guiter guiter;

  void sing() {
    guiter = context.getBean("gopher", Guiter.class);
    guiter.sing();
  }
}
