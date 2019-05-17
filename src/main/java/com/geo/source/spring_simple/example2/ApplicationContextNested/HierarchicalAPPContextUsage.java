package com.geo.source.spring_simple.example2.ApplicationContextNested;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 嵌套注入测试
 * context-child.xml、context-parent.xml
 *
 * @author YanZhen
 * @since 2019-05-17 10:05
 */
public class HierarchicalAPPContextUsage {
  public static void main(String[] args) {
    final GenericXmlApplicationContext contextParent = new GenericXmlApplicationContext();
    contextParent.load("classpath:spring/context-parent.xml");
    contextParent.refresh();
    final GenericXmlApplicationContext contextChild = new GenericXmlApplicationContext();
    contextChild.load("classpath:spring/context-child.xml");
    contextChild.setParent(contextParent);
    contextChild.refresh();
    final Song song1 = (Song) contextChild.getBean("song1");
    final Song song2 = (Song) contextChild.getBean("song2");
    final Song song3 = (Song) contextChild.getBean("song3");
    System.out.println(song1.getTitle());
    System.out.println(song2.getTitle());
    System.out.println(song3.getTitle());
    contextParent.close();
    contextChild.close();
  }
}
