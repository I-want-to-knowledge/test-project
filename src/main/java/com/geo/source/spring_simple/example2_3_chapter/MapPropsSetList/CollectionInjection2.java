package com.geo.source.spring_simple.example2_3_chapter.MapPropsSetList;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 集合的注入测试
 * app-context-collection2.xml
 *
 * @author YanZhen
 * @since 2019-05-17 12:29
 */
@Service
public class CollectionInjection2 {
  @Resource
  private Map<String, Object> map;
  @Resource
  private Properties properties;
  @Resource
  private Set<String> set;
  @Resource
  private List<String> list;

  public static void main(String[] args) {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:spring/app-context-collection2.xml");
    context.refresh();
    final CollectionInjection2 collectionInjection = (CollectionInjection2) context.getBean("collectionInjection2");
    collectionInjection.displayInfo();
    context.close();
  }

  private void displayInfo() {
    System.out.println("\n==========Map contents:=========");
    map.forEach((k, v) -> System.out.println("key : " + k + " - value : " + v));
    System.out.println("\n==========Properties contents:=========");
    properties.forEach((k, v) -> System.out.println("key : " + k + " - value : " + v));
    System.out.println("\n==========Set contents:=========");
    set.forEach(v -> System.out.println("value : " + v));
    System.out.println("\n==========List contents:=========");
    list.forEach(v -> System.out.println("value : " + v));
  }
}
