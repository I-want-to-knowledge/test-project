package com.geo.source.spring_simple.example2_3_chapter.MapPropsSetList;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 集合的注入测试
 * app-context-collection.xml
 *
 * @author YanZhen
 * @since 2019-05-17 12:29
 */
public class CollectionInjection {
  private Map<String, Object> map;
  private Properties properties;
  private Set<String> set;
  private List<String> list;

  public static void main(String[] args) {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:spring/app-context-collection.xml");
    context.refresh();
    final CollectionInjection collectionInjection = (CollectionInjection) context.getBean("collectionInjection");
    collectionInjection.displayInfo();
    context.close();
  }

  private void displayInfo() {
    System.out.println("==========Map contents:=========");
    map.forEach((k, v) -> System.out.println("key : " + k + " - value : " + v));
    System.out.println("==========Properties contents:=========");
    properties.forEach((k, v) -> System.out.println("key : " + k + " - value : " + v));
    System.out.println("==========Set contents:=========");
    set.forEach(v -> System.out.println("value : " + v));
    System.out.println("==========List contents:=========");
    list.forEach(v -> System.out.println("value : " + v));
  }

  public void setMap(Map<String, Object> map) {
    this.map = map;
  }

  public void setProperties(Properties properties) {
    this.properties = properties;
  }

  public void setSet(Set<String> set) {
    this.set = set;
  }

  public void setList(List<String> list) {
    this.list = list;
  }
}
