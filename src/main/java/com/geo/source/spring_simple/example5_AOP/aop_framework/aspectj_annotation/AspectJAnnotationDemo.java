package com.geo.source.spring_simple.example5_AOP.aop_framework.aspectj_annotation;

import org.springframework.context.annotation.*;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 使用Aspect注解
 *
 * @author YanZhen
 * @since 2019-06-06 14:39
 */
public class AspectJAnnotationDemo {
  public static void main(String[] args) {
    final GenericApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    final NewDocumentarist2 newDocumentarist2 = context.getBean("newDocumentarist2", NewDocumentarist2.class);
    newDocumentarist2.execute();
    context.close();
  }
}

@Configuration
@ComponentScans({@ComponentScan("com.geo.source.spring_simple.example5_AOP.aop_framework.aspectj_annotation"),
        @ComponentScan("com.geo.source.spring_simple.example5_AOP.pointcut.static_name_pointcut")})
@EnableAspectJAutoProxy(proxyTargetClass = true) // 与xml中的<aop:aspectj-autoproxy/>等同，用于扫描@AspectJ注解
class AppConfig {
}

