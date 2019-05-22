package com.geo.source.spring_simple.example2_3_chapter.stereotype;

import com.geo.source.spring_simple.example.MessageRenderer;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * spring 控件
 *
 * @author YanZhen
 * @since 2019-05-16 12:53
 */
public class DeclareSpringComponents {
  public static void main(String[] args) {
    final GenericXmlApplicationContext xmlApplicationContext = new GenericXmlApplicationContext();
    // xmlApplicationContext.load("classpath:spring/app-context-xml.xml");
    xmlApplicationContext.load("classpath:spring/app-context-annotation.xml");
    xmlApplicationContext.refresh();
    final MessageRenderer renderer = xmlApplicationContext.getBean("renderer", MessageRenderer.class);
    renderer.render();
    xmlApplicationContext.close();
  }
}
