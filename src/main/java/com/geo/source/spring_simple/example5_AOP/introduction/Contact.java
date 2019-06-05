package com.geo.source.spring_simple.example5_AOP.introduction;

/**
 * 被代理的对象
 *
 * @author YanZhen
 * @since 2019-06-05 09:43
 */
public class Contact {
  private String name;
  private String phoneNumbers;
  private String email;

  public String getName() {
    return name;
  }

  public String getPhoneNumbers() {
    return phoneNumbers;
  }

  public String getEmail() {
    return email;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPhoneNumbers(String phoneNumbers) {
    this.phoneNumbers = phoneNumbers;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
