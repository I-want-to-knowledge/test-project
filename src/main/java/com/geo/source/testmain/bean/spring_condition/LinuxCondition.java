package com.geo.source.testmain.bean.spring_condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Linux 环境
 *
 * @author YanZhen
 * @since 2019-03-28 15:54
 */
public class LinuxCondition implements Condition {

  @Override
  public boolean matches(ConditionContext var, AnnotatedTypeMetadata var2) {
    Environment environment = var.getEnvironment();
    String property = environment.getProperty("os.name");
    return property != null && property.startsWith("Linux");
  }
}
