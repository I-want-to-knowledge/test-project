package com.geo.source.spring_simple.example4_chapter.property_source;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 属性文件测试
 *
 * @author YanZhen
 * @since 2019-05-27 16:20
 */
public class APPProperty {
  private String applicationHome;
  private String userHome;

  public String getApplicationHome() {
    return applicationHome;
  }

  public String getUserHome() {
    return userHome;
  }

  public void setApplicationHome(String applicationHome) {
    this.applicationHome = applicationHome;
  }

  public void setUserHome(String userHome) {
    this.userHome = userHome;
  }

  @Override
  public String toString() {
    return "applicationHome : " + applicationHome + "\nuserHome : " + userHome;
  }
}

class Property {
  public static void main(String[] args) {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:spring2/app-context-property-editor.xml");
    context.refresh();
    final APPProperty appProperty = context.getBean("appProperty", APPProperty.class);
    System.out.println(appProperty);
    context.close();
  }
}
