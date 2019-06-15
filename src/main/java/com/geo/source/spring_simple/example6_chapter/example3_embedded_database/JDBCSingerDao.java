package com.geo.source.spring_simple.example6_chapter.example3_embedded_database;

import com.geo.source.spring_simple.example6_chapter.entity1.Singer;
import com.geo.source.spring_simple.example6_chapter.example1_jdbc.SingerDao;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * jdbc 数据访问模型 dao实现
 *
 * @author YanZhen
 * @since 2019-06-12 16:53
 */
public class JDBCSingerDao implements SingerDao, InitializingBean {

  private DataSource dataSource;
  private JdbcTemplate jdbcTemplate;

  /*public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
    final JdbcTemplate jdbcTemplate = new JdbcTemplate();
    jdbcTemplate.setDataSource(dataSource);
    final MySQLErrorCodesTranslator errorCodesTranslator = new MySQLErrorCodesTranslator();
    errorCodesTranslator.setDataSource(dataSource);
    jdbcTemplate.setExceptionTranslator(errorCodesTranslator);
    this.jdbcTemplate = jdbcTemplate;
  }*/

  void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.dataSource = jdbcTemplate.getDataSource();
    final MySQLErrorCodesTranslator errorCodesTranslator = new MySQLErrorCodesTranslator();
    errorCodesTranslator.setDataSource(dataSource);
    jdbcTemplate.setExceptionTranslator(errorCodesTranslator);
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void afterPropertiesSet() {
    if (dataSource == null) {
      throw new BeanCreationException("Must set dataSource on SingerDao");
    }
  }

  @Override
  public String findNameById(Long id) {
    return jdbcTemplate.queryForObject("select first_name || ' ' || last_name from singer where id = ?",
            new Object[]{id}, String.class);
  }

  @Override
  public List<Singer> findAll() {
    exception("findAll");
    return null;
  }

  @Override
  public List<Singer> findByFirstName(String firstName) {
    exception("findByFirstName");
    return null;
  }

  @Override
  public String findLastNameById(Long id) {
    exception("findLastNameById");
    return null;
  }

  @Override
  public String findFirstNameById(Long id) {
    exception("findFirstNameById");
    return null;
  }

  @Override
  public void insert(Singer singer) {
    exception("insert");
  }

  @Override
  public void update(Singer singer) {
    exception("update");
  }

  @Override
  public void delete(Long singerId) {
    exception("delete");
  }

  @Override
  public List<Singer> findAllWithDetail() {
    exception("findAllWithDetail");
    return null;
  }

  @Override
  public void insertWithDetail(Singer singer) {
    exception("insertWithDetail");
  }

  private void exception(String methodName) {
    throw new NotImplementedException(methodName);
  }
}
