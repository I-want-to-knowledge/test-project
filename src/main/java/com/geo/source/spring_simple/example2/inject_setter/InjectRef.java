package com.geo.source.spring_simple.example2.inject_setter;

import com.geo.source.spring_simple.example2.BeanFactoryImpl.Oracle;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 通过ref注入依赖
 *
 * @author YanZhen
 * @since 2019-05-16 17:51
 */
public class InjectRef {
  private Oracle oracle;

  public void setOracle(Oracle oracle) {
    this.oracle = oracle;
  }

  public static void main(String[] args) {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:spring/xml-bean-factory-config.xml");
    context.refresh();
    InjectRef injectRef = (InjectRef) context.getBean("injectRef");
    System.out.println(injectRef);
    context.close();
  }

  @Override
  public String toString() {
    return oracle.defineMeaningOfLife();
  }
}
