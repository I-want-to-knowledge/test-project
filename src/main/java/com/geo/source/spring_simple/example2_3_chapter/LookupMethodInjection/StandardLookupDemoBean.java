package com.geo.source.spring_simple.example2_3_chapter.LookupMethodInjection;

/**
 * 标准查找方法注入
 *
 * @author YanZhen
 * @since 2019-05-18 13:10
 */
public class StandardLookupDemoBean implements DemoBean {
  private Singer mySinger;

  public void setMySinger(Singer singer) {
    this.mySinger = singer;
  }

  @Override
  public Singer getMySinger() {
    return this.mySinger;
  }

  @Override
  public void doSomething() {
    mySinger.sing();
  }
}
