package com.geo.source.spring_simple.example2_3_chapter.SpEL;

import org.springframework.stereotype.Component;

/**
 * 已注入的形式赋值
 * app-context-annotation.xml
 *
 * @author YanZhen
 * @since 2019-05-16 17:14
 */
@Component
public class InjectSimpleConfig2 {
  /*private String name = "YZ";
  private int age = 26;
  private float height = 1.78F;
  private boolean programmer = true;
  private Long ageInSeconds = 123456789L;*/

  public String getName() {
    return "YZ";
  }

  public int getAge() {
    return 26;
  }

  public float getHeight() {
    return 1.78F;
  }

  public boolean isProgrammer() {
    return true;
  }

  public Long getAgeInSeconds() {
    return 123456789L;
  }
}
