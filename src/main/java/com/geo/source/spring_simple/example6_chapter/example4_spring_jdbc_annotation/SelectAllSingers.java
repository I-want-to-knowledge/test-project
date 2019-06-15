package com.geo.source.spring_simple.example6_chapter.example4_spring_jdbc_annotation;

import com.geo.source.spring_simple.example6_chapter.entity1.Singer;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * MappingSqlQuery 测试
 * 自定义的SqlQuery查询工具
 *
 * @author YanZhen
 * @since 2019-06-13 16:00
 */
public class SelectAllSingers extends MappingSqlQuery<Singer> {

  private static final String SQL_SELECT_ALL = "select id,first_name,last_name,birth_date from singer";

  SelectAllSingers(DataSource ds) {
    super(ds, SQL_SELECT_ALL);
  }

  @Override
  protected Singer mapRow(ResultSet rs, int rowNum) throws SQLException {
    final Singer singer = new Singer();
    singer.setId(rs.getLong("id"));
    singer.setFirstName(rs.getString("first_name"));
    singer.setLastName(rs.getString("last_name"));
    singer.setBirthDate(rs.getDate("birth_date").toLocalDate());
    return singer;
  }
}
