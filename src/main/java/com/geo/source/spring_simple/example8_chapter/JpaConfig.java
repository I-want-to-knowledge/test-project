package com.geo.source.spring_simple.example8_chapter;

import org.hibernate.dialect.H2Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * jpa 2 数据访问
 *
 * @author YanZhen
 * @since 2019-06-18 14:42
 */
@Configuration
@EnableTransactionManagement
@ComponentScan("com.geo.source.spring_simple.example8_chapter")
@EnableJpaRepositories({"com.geo.source.spring_simple.example8_chapter"})// Spring data JPA 存储库唯一配置元素，扫描Repository扩展类
public class JpaConfig {
  @Bean
  public DataSource dataSource() {
    final EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
    return databaseBuilder.setType(EmbeddedDatabaseType.H2).addScripts("sql/schema.sql", "sql/test-data.sql").build();
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
    return new JpaTransactionManager(entityManagerFactory());
  }

  @Bean
  public JpaVendorAdapter jpaVendorAdapter() {
    return new HibernateJpaVendorAdapter();
  }

  @Bean
  public Properties hibernateProperties() {
    final Properties hibernateProperties = new Properties();
    hibernateProperties.put("hibernate.dialect", H2Dialect.class);
    hibernateProperties.put("hibernate.format_sql", true);
    hibernateProperties.put("hibernate.use_sql_comments", true);
    hibernateProperties.put("hibernate.show_sql", true);
    hibernateProperties.put("hibernate.max_fetch_depth", 3);
    hibernateProperties.put("hibernate.jdbc.batch_size", 10);
    hibernateProperties.put("hibernate.jdbc.fetch_size", 50);
    hibernateProperties.put("hibernate.enable_lazy_load_no_trans", true);
    return hibernateProperties;
  }

  @Bean
  public EntityManagerFactory entityManagerFactory() {
    final LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactory.setPackagesToScan("com.geo.source.spring_simple.example8_chapter");
    entityManagerFactory.setDataSource(dataSource());
    // entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    entityManagerFactory.setJpaProperties(hibernateProperties());
    entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter());
    entityManagerFactory.afterPropertiesSet();
    return entityManagerFactory.getNativeEntityManagerFactory();
  }
}
