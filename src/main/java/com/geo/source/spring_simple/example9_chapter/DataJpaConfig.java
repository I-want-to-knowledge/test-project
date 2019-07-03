package com.geo.source.spring_simple.example9_chapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * 配置类
 *
 * @author YanZhen
 * @since 2019-07-01 17:37
 */
@Configuration
@EnableJpaRepositories({"com.geo.source.spring_simple.example9_chapter"})
public class DataJpaConfig {
  private Logger logger = LoggerFactory.getLogger(DataJpaConfig.class);

  @Bean
  public DataSource dataSource() {
    final SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
    dataSource.setDriverClass(org.h2.Driver.class);
    dataSource.setUrl("jdbc:h2:mem:spring_jdbc_test");// jdbc:h2:mem:DB数据库名
    dataSource.setUsername("yz");
    dataSource.setPassword("yz");

    return dataSource;
  }

  @Bean
  public Properties hibernateProperties() {
    final Properties properties = new Properties();
    properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
    properties.put("hibernate.hbm2ddl.auto", "create-drop");// create-drop
    properties.put("hibernate.show_sql", true);
    properties.put("hibernate.max_fetch_depth", 3);
    properties.put("hibernate.jdbc.batch_size", 10);
    properties.put("hibernate.jdbc.fetch_size", 50);

    return properties;
  }

  @Bean
  public JpaVendorAdapter jpaVendorAdapter() {
    return new HibernateJpaVendorAdapter();
  }

  @Bean
  public EntityManagerFactory entityManagerFactory() {
    final LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
    factoryBean.setPackagesToScan("com.geo.source.spring_simple.example9_chapter");
    factoryBean.setDataSource(dataSource());
    factoryBean.setJpaProperties(hibernateProperties());
    factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
    factoryBean.afterPropertiesSet();
    return factoryBean.getNativeEntityManagerFactory();
  }
}
