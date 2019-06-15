package com.geo.source.spring_simple.example6_chapter.example2_spring_jdbc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

/**
 * data source 配置
 *
 * @author YanZhen
 * @since 2019-06-12 12:40
 */
@Configuration
@PropertySource("classpath:spring6/jdbc2.properties")
public class DbConfig {
  @Value("${jdbc.driverClassName}")
  private String driverClassName;
  @Value("${jdbc.url}")
  private String url;
  @Value("${jdbc.username}")
  private String username;// 没有找到与local-override对应的属性
  @Value("${jdbc.password}")
  private String password;

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  @Bean
  @Lazy
  public DataSource dataSource() {
    final SimpleDriverDataSource driverDataSource = new SimpleDriverDataSource();
    try {
      driverDataSource.setDriverClass((Class<? extends Driver>) Class.forName(driverClassName));
      driverDataSource.setUrl(url);
      driverDataSource.setUsername(username);
      driverDataSource.setPassword(password);
    } catch (ClassNotFoundException e) {
      System.err.println("异常：" + e.getMessage());
      return null;
    }
    return driverDataSource;
  }
}
