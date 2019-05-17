package com.geo.source.spring_simple.example2.SpEL;

/**
 * 已配置项的形式赋值
 * app-context.xml
 *
 * @author YanZhen
 * @since 2019-05-16 17:14
 */
public class InjectSimpleConfig {
  private String name = "YZ";
  private int age = 26;
  private float height = 1.78F;
  private boolean programmer = true;
  private Long ageInSeconds = 123456789L;

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public float getHeight() {
    return height;
  }

  public boolean isProgrammer() {
    return programmer;
  }

  public Long getAgeInSeconds() {
    return ageInSeconds;
  }
}
