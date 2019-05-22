package com.geo.source.spring_simple.CircularBeanTest;

import javax.annotation.Resource;

/**
 * bean之间的相互依赖注入顺序
 *
 * @author YanZhen
 * @since 2019-05-21 17:56
 */
public class AA {

  private BB bb;

  public AA() {
  }

  public AA(BB bb) {
    this.bb = bb;
    System.out.println("inject BB:" + this.bb);
  }

  @Resource
  public void setBb(BB bb) {
    this.bb = bb;
    System.out.println(this.toString() + " setter inject BB");
  }

  @Override
  public String toString() {
    return "class AA";
  }
}
