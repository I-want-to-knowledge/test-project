package com.geo.source.spring_simple.example6_chapter.example4_spring_jdbc_annotation;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.BatchSqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * 批量处理  BatchSqlUpdate
 *
 * @author YanZhen
 * @since 2019-06-14 15:18
 */
class InsertSingerAlbum extends BatchSqlUpdate {
  private static final String SQL_INSERTS = "insert into album (singer_id, title, release_date) " +
          "values (:singer_id, :title, :release_date)";
  InsertSingerAlbum(DataSource ds) {
    super(ds, SQL_INSERTS);
    super.declareParameter(new SqlParameter("singer_id", Types.VARCHAR));
    super.declareParameter(new SqlParameter("title", Types.VARCHAR));
    super.declareParameter(new SqlParameter("release_date", Types.DATE));

    setBatchSize(10);// 批处理大小
  }
}
