package com.geo.source.testmain.bean.spring_condition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

/**
 * test
 *
 * @author YanZhen
 * @since 2019-03-28 15:43
 */
public class ConditionMain {
  public static void main(String[] args) {
    // 扫描
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
    Map<String, Person> map = applicationContext.getBeansOfType(Person.class);
    ConfigurableEnvironment environment = applicationContext.getEnvironment();
    String property = environment.getProperty("os.name");
    System.out.println(property);
    System.out.println(map);
  }
}
