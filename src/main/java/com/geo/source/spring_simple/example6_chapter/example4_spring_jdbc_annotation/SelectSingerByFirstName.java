package com.geo.source.spring_simple.example6_chapter.example4_spring_jdbc_annotation;

import com.geo.source.spring_simple.example6_chapter.entity1.Singer;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * MappingSqlQuery 测试
 * 自定义的SqlQuery查询工具
 *
 * @author YanZhen
 * @since 2019-06-13 17:22
 */
public class SelectSingerByFirstName extends MappingSqlQuery<Singer> {

  private static final String SQL_SELECT_FIRST_NAME = "select first_name,last_name,birth_date" +
          " from singer where first_name = :first_name";

  SelectSingerByFirstName(DataSource dataSource) {
    super(dataSource, SQL_SELECT_FIRST_NAME);
    super.declareParameter(new SqlParameter("first_name", Types.VARCHAR));
  }

  @Override
  protected Singer mapRow(ResultSet resultSet, int i) throws SQLException {
    final Singer singer = new Singer();
    singer.setFirstName(resultSet.getString("first_name"));
    singer.setLastName(resultSet.getString("last_name"));
    singer.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
    return singer;
  }
}
