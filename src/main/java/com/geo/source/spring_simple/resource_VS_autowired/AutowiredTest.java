package com.geo.source.spring_simple.resource_VS_autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Autowired 注入测试
 *
 * @author YanZhen
 * @since 2019-05-17 14:32
 */
@Service
public class AutowiredTest {
  @Autowired
  private List<ResourceAutowired> resourceAutowiredList;

  /*public AutowiredTest(List<ResourceAutowired> resourceAutowiredList) {
    this.resourceAutowiredList = resourceAutowiredList;
  }*/

  @Autowired  // 该注入会报 类转换异常
  @Qualifier("resourceAutowired")
  private ResourceAutowired resourceAutowired;

  @Autowired  // 该注入会报 类转换异常
  @Qualifier("resourceAutowired2")
  private ResourceAutowired resourceAutowired2;

  public static void main(String[] args) {
    final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ResourceAutowiredConfiguration.class);
    final AutowiredTest resourceTest = (AutowiredTest) context.getBean("autowiredTest");
    System.out.println(resourceTest);
  }

  @Override
  public String toString() {
    resourceAutowiredList.forEach(v-> System.out.println(v.toString()));
    return "";
  }
}
