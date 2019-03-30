package com.geo.source.testmain.bean.spring_condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Windows 环境
 *
 * @author YanZhen
 * @since 2019-03-28 15:46
 */
public class WindowsCondition implements Condition {

  @Override
  public boolean matches(ConditionContext var, AnnotatedTypeMetadata var2) {
    ConfigurableListableBeanFactory beanFactory = var.getBeanFactory();// 获取ioc使用的bean factory
    ClassLoader classLoader = var.getClassLoader();// 获取类加载器
    Environment environment = var.getEnvironment();// 获取当前环境信息
    BeanDefinitionRegistry registry = var.getRegistry();// 获取bean定义的注册类

    // 获取当前系统名
    String property = environment.getProperty("os.name");
    return property != null && property.startsWith("Windows");
  }
}
