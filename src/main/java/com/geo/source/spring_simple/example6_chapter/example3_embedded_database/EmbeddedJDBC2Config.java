package com.geo.source.spring_simple.example6_chapter.example3_embedded_database;

import com.geo.source.spring_simple.example6_chapter.example1_jdbc.SingerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
public class EmbeddedJDBC2Config {
  private static Logger logger = LoggerFactory.getLogger(EmbeddedJDBC2Config.class);

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
  public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
    return new NamedParameterJdbcTemplate(dataSource());
  }

  @Bean
  public SingerDao singerDao() {
    final JDBCSinger2Dao jdbcSinger2Dao = new JDBCSinger2Dao();
    jdbcSinger2Dao.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate());
    return jdbcSinger2Dao;
  }
}
