package com.geo.source.spring_simple.CircularBeanTest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * bean之间的相互依赖注入顺序
 *
 * @author YanZhen
 * @since 2019-05-21 18:01
 */
public class BeanConfiguration {

  public static void main(String[] args) {
    // 循环依赖正常注入
    final GenericApplicationContext setterInjectContext = new AnnotationConfigApplicationContext(SetterInject.class);
    setterInjectContext.close();

    // 循环依赖注入失败， 异常信息UnsatisfiedDependencyException（不满足依赖异常）
    final GenericApplicationContext constructorInjectContext = new AnnotationConfigApplicationContext(ConstructorInject.class);
    constructorInjectContext.close();
  }
}
