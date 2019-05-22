package com.geo.source.spring_simple.CircularBeanTest;

import javax.annotation.Resource;

/**
 * bean之间的相互依赖注入顺序
 *
 * @author YanZhen
 * @since 2019-05-21 17:57
 */
public class CC {
  private AA aa;

  public CC() {
  }

  public CC(AA aa) {
    this.aa = aa;
    System.out.println("inject AA:" + this.aa);
  }

  @Resource
  public void setAa(AA aa) {
    this.aa = aa;
    System.out.println(this.toString() + " setter inject AA");
  }

  @Override
  public String toString() {
    return "class CC";
  }
}
