package com.geo.source.spring_simple.example2_3_chapter.LookupMethodInjection;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

/**
 * 抽象的查找方法注入 例2
 *
 * @author YanZhen
 * @since 2019-05-18 13:13
 */
@Component
public class AbstractLookupDemoBean2 implements DemoBean2 {
  @Lookup
  public Singer2 getMySinger2() {
    return null;
  }

  @Override
  public void doSomething() {
    getMySinger2().sing();
  }
}
