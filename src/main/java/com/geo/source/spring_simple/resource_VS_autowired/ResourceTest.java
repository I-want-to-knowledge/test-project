package com.geo.source.spring_simple.resource_VS_autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * resource 注入测试
 *
 * @author YanZhen
 * @since 2019-05-17 14:32
 */
@Service
public class ResourceTest {
  @Resource
  private List<ResourceAutowired> resourceAutowiredList;

  public static void main(String[] args) {
    final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ResourceAutowiredConfiguration.class);
    final ResourceTest resourceTest = (ResourceTest) context.getBean("resourceTest");
    System.out.println(resourceTest);
  }

  @Override
  public String toString() {
    resourceAutowiredList.forEach(v-> System.out.println(v.toString()));
    return "";
  }
}
