//package com.geo.source.spring_simple.example7_chapter;
//
//import org.hibernate.SessionFactory;
//import org.hibernate.dialect.H2Dialect;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
//import org.springframework.orm.hibernate5.HibernateTransactionManager;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.io.IOException;
//import java.util.Properties;
//
///**
// * hibernate框架， 配置项
// *
// * @author YanZhen
// * @since 2019-06-17 09:44
// */
//@Configuration
//@ComponentScan("com.geo.source.spring_simple.example7_chapter")
//@EnableTransactionManagement
//public class APPConfig {
//  private static Logger logger = LoggerFactory.getLogger(APPConfig.class);
//
//  @Bean
//  public DataSource dataSource() {
//    final EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
//    return databaseBuilder.setType(EmbeddedDatabaseType.H2).addScripts("sql/schema.sql", "sql/test-data.sql").build();
//  }
//
//  private Properties hibernateProperties() {
//    final Properties hibernateProp = new Properties();
//    hibernateProp.put("hibernate.dialect", H2Dialect.class);
//    hibernateProp.put("hibernate.format_sql", true);
//    hibernateProp.put("hibernate.use_sql_comments", true);
//    hibernateProp.put("hibernate.show_sql", true);
//    hibernateProp.put("hibernate.max_fetch_depth", 3);
//    hibernateProp.put("hibernate.batch_size", 10);
//    hibernateProp.put("hibernate.fetch_size", 50);
//    // 懒惰初始化功能关闭，防止（org.hibernate.LazyInitializationException：could not initialize proxy - no Session）异常发生
//    // hibernateProp.put("hibernate.enable_lazy_load_no_trans", true);
//    return hibernateProp;
//  }
//
//  @Bean
//  public SessionFactory sessionFactory() throws IOException {
//    final LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
//    sessionFactoryBean.setDataSource(dataSource());
//    sessionFactoryBean.setPackagesToScan("com.geo.source.spring_simple.example7_chapter");
//    sessionFactoryBean.setHibernateProperties(hibernateProperties());
//    sessionFactoryBean.afterPropertiesSet();
//    return sessionFactoryBean.getObject();
//  }
//
//  @Bean
//  public PlatformTransactionManager transactionManager() throws IOException {
//    return new HibernateTransactionManager(sessionFactory());
//  }
//}
