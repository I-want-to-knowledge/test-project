package com.geo.source.spring_simple.example6_chapter.example4_spring_jdbc_annotation;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlFunction;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * SqlFunction 调用存储函数
 *
 * @author YanZhen
 * @since 2019-06-14 17:28
 */
class StoredFunctionFirstNameById extends SqlFunction<String> {
  private static final String SQL_FUNCTION = "SELECT getFirstNameById(?)";
  StoredFunctionFirstNameById(DataSource ds) {
    super(ds, SQL_FUNCTION);
    declareParameter(new SqlParameter(Types.INTEGER));
    compile();
  }
}
