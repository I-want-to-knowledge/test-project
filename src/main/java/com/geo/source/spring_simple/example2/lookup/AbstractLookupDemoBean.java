package com.geo.source.spring_simple.example2.lookup;

/**
 * 抽象的查找方法注入
 *
 * @author YanZhen
 * @since 2019-05-18 13:13
 */
public abstract class AbstractLookupDemoBean implements DemoBean {
  public abstract Singer getMySinger();

  @Override
  public void doSomething() {
    getMySinger().sing();
  }
}
