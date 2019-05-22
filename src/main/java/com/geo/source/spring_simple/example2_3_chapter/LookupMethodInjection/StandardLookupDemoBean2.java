package com.geo.source.spring_simple.example2_3_chapter.LookupMethodInjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 标准查找方法注入 例2
 *
 * @author YanZhen
 * @since 2019-05-18 13:10
 */
@Component
public class StandardLookupDemoBean2 implements DemoBean2 {
  private Singer2 mySinger2;

  @Autowired
  public void setMySinger2(Singer2 singer2) {
    this.mySinger2 = singer2;
  }

  @Override
  public Singer2 getMySinger2() {
    return this.mySinger2;
  }

  @Override
  public void doSomething() {
    mySinger2.sing();
  }
}
