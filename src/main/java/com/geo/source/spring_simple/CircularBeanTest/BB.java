package com.geo.source.spring_simple.CircularBeanTest;

import javax.annotation.Resource;

/**
 * bean之间的相互依赖注入顺序
 *
 * @author YanZhen
 * @since 2019-05-21 17:57
 */
public class BB {
  private CC cc;

  public BB() {
  }

  public BB(CC cc) {
    this.cc = cc;
    System.out.println("inject CC:" + this.cc);
  }

  @Resource
  public void setCc(CC cc) {
    this.cc = cc;
    System.out.println(this.toString() + " setter inject CC");
  }

  @Override
  public String toString() {
    return "class BB";
  }
}
