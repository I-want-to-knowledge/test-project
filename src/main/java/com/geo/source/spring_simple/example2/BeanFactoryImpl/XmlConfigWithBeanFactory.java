package com.geo.source.spring_simple.example2.BeanFactoryImpl;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * bean factory
 *
 * @author YanZhen
 * @since 2019-05-15 15:46
 */
public class XmlConfigWithBeanFactory {
  public static void main(String[] args) {
    final DefaultListableBeanFactory listableBeanFactory = new DefaultListableBeanFactory();
    final XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(listableBeanFactory);
    beanDefinitionReader.loadBeanDefinitions(new ClassPathResource("spring/xml-bean-factory-config.xml"));
    final Oracle oracle = (Oracle) listableBeanFactory.getBean("oracle");
    System.out.println(oracle.defineMeaningOfLife());
  }
}
