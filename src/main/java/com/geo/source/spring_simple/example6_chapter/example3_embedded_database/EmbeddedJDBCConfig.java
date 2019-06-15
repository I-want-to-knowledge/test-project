package com.geo.source.spring_simple.example6_chapter.example3_embedded_database;

import com.geo.source.spring_simple.example6_chapter.example1_jdbc.SingerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * 嵌入数据库，配置
 *
 * @author YanZhen
 * @since 2019-06-12 15:06
 */
@Configuration
public class EmbeddedJDBCConfig {
  private static Logger logger = LoggerFactory.getLogger(EmbeddedJDBCConfig.class);

  @Bean
  public DataSource dataSource() {
    try {
      final EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
      return databaseBuilder.setType(EmbeddedDatabaseType.H2).addScripts("sql/schema.sql", "sql/test-data.sql").build();
    } catch (Exception e) {
      logger.error("Embedded Database bean cannot be created!", e);
      return null;
    }
  }

  @Bean
  public JdbcTemplate jdbcTemplate() {
    final JdbcTemplate jdbcTemplate = new JdbcTemplate();
    jdbcTemplate.setDataSource(dataSource());
    return jdbcTemplate;
  }

  @Bean
  public SingerDao singerDao() {
    final JDBCSingerDao jdbcSingerDao = new JDBCSingerDao();
    jdbcSingerDao.setJdbcTemplate(jdbcTemplate());
    return jdbcSingerDao;
  }
}
