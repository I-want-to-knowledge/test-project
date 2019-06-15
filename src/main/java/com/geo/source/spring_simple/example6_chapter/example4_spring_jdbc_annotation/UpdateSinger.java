package com.geo.source.spring_simple.example6_chapter.example4_spring_jdbc_annotation;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * 修改歌手信息
 *
 * @author YanZhen
 * @since 2019-06-14 11:31
 */
class UpdateSinger extends SqlUpdate {
  private static final String SQL_UPDATE = "update singer" +
          " set first_name=:first_name,last_name=:last_name,birth_date=:birth_date where id=:id";

  UpdateSinger(DataSource ds) {
    super(ds, SQL_UPDATE);
    super.declareParameter(new SqlParameter("first_name", Types.VARCHAR));
    super.declareParameter(new SqlParameter("last_name", Types.VARCHAR));
    super.declareParameter(new SqlParameter("birth_date", Types.DATE));
    super.declareParameter(new SqlParameter("id", Types.INTEGER));
  }
}
