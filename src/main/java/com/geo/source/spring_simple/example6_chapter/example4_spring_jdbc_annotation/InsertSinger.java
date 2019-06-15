package com.geo.source.spring_simple.example6_chapter.example4_spring_jdbc_annotation;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * 插入歌手
 *
 * @author YanZhen
 * @since 2019-06-14 13:49
 */
public class InsertSinger extends SqlUpdate {
  private static final String SQL_INSERT = "insert into singer (first_name, last_name, birth_date) " +
          "values (:first_name, :last_name, :birth_date)";

  public InsertSinger(DataSource ds) {
    super(ds, SQL_INSERT);
    super.declareParameter(new SqlParameter("first_name", Types.VARCHAR));
    super.declareParameter(new SqlParameter("last_name", Types.VARCHAR));
    super.declareParameter(new SqlParameter("birth_date", Types.DATE));
    super.setGeneratedKeysColumnNames("id");
    super.setReturnGeneratedKeys(true);
  }
}
