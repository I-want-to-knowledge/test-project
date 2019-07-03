package com.geo.source.spring_simple.example9_chapter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;

/**
 * 事务测试
 *
 * @author YanZhen
 * @since 2019-07-02 14:08
 */
@Configuration
@EnableTransactionManagement
@ComponentScan("com.geo.source.spring_simple.example9_chapter")
public class ServicesConfig {

  @Resource
  private EntityManagerFactory entityManagerFactory;

  @Bean
  public PlatformTransactionManager transactionManager() {// 必须名为 transactionManager
    return new JpaTransactionManager(entityManagerFactory);
  }
}
