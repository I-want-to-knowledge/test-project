package com.geo.source.spring_simple.example6_chapter.example4_spring_jdbc_annotation;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * jdbc 注解
 *
 * @author YanZhen
 * @since 2019-06-13 14:31
 */
@Configuration
@PropertySource("classpath:spring6/jdbc2.properties")
@ComponentScan("com.geo.source.spring_simple.example6_chapter.example4_spring_jdbc_annotation")
public class AppConfig {
  @Value("${jdbc.driverClassName}")
  private String driverClassName;
  @Value("${jdbc.url}")
  private String url;
  @Value("${jdbc.username}")
  private String username;
  @Value("${jdbc.password}")
  private String password;

  /*@Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    final PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
    configurer.setLocalOverride(true);// jdbc.properties 配置 先读取本地属性
    return configurer;
  }*/

  @Bean
  public DataSource dataSource() {
    final BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName(driverClassName);
    dataSource.setUrl(url);
    dataSource.setUsername(username);
    dataSource.setPassword(password);
    return dataSource;
  }
}
