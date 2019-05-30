package com.geo.source.spring_simple.example5_AOP.advice.secure_before_advice;

/**
 * 用户信息
 *
 * @author YanZhen
 * @since 2019-05-29 15:56
 */
public class UserInfo {
  private String name;
  private String pwd;

  UserInfo(String name, String pwd) {
    this.name = name;
    this.pwd = pwd;
  }

  public String getPwd() {
    return pwd;
  }

  public String getName() {
    return name;
  }
}
