package com.geo.source.spring_simple.example4_chapter.property_source;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 环境测试
 *
 * @author YanZhen
 * @since 2019-05-27 15:38
 */
public class EnvironmentSample {
  public static void main(String[] args) {
    m1();
    System.out.println("==============================");
    m2();
  }

  /**
   * addFirst 方法添加
   */
  private static void m2() {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.refresh();

    final ConfigurableEnvironment environment = context.getEnvironment();
    final MutablePropertySources propertySources = environment.getPropertySources();
    final Map<String, Object> map = new HashMap<>();
    map.put("user.home", "application_home");
    propertySources.addFirst(new MapPropertySource("proSpring5 Map", map));
    System.out.println("user.home: " + System.getProperty("user.home"));
    System.out.println("JAVA_HOME: " + System.getenv("JAVA_HOME"));
    System.out.println("user.home: " + environment.getProperty("user.home"));
    System.out.println("JAVA_HOME: " + environment.getProperty("JAVA_HOME"));

    context.close();
  }

  /**
   * addLast 方法添加
   */
  private static void m1() {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.refresh();

    final ConfigurableEnvironment environment = context.getEnvironment();
    final MutablePropertySources propertySources = environment.getPropertySources();
    final Map<String, Object> map = new HashMap<>();
    map.put("user.home", "application_home");
    propertySources.addLast(new MapPropertySource("proSpring5 Map", map));
    System.out.println("user.home: " + System.getProperty("user.home"));
    System.out.println("JAVA_HOME: " + System.getenv("JAVA_HOME"));
    System.out.println("user.home: " + environment.getProperty("user.home"));
    System.out.println("JAVA_HOME: " + environment.getProperty("JAVA_HOME"));
    System.out.println("application.home: " + Objects.requireNonNull(propertySources.get("proSpring5 Map")).getProperty("user.home"));

    context.close();
  }
}
