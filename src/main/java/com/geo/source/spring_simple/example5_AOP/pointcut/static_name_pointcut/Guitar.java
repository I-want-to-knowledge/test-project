package com.geo.source.spring_simple.example5_AOP.pointcut.static_name_pointcut;

/**
 * 吉他
 *
 * @author YanZhen
 * @since 2019-05-31 10:59
 */
public class Guitar {
  private String brand = " Martin";

  public String play() {
    return "G C G C Am D7";
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }
}
