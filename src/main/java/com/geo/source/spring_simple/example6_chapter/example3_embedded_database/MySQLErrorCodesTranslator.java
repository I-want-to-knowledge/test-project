package com.geo.source.spring_simple.example6_chapter.example3_embedded_database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

import java.sql.SQLException;

/**
 * sql，异常处理
 *
 * @author YanZhen
 * @since 2019-06-12 17:02
 */
public class MySQLErrorCodesTranslator extends SQLErrorCodeSQLExceptionTranslator {
  private static Logger logger = LoggerFactory.getLogger(MySQLErrorCodesTranslator.class);
  @Override
  protected DataAccessException customTranslate(String task, String sql, SQLException sqlEx) {
    logger.error("异常发生：" + sqlEx.getErrorCode());
    if (sqlEx.getErrorCode() == -12345) {
      return new DeadlockLoserDataAccessException(task, sqlEx);
    }
    return null;
  }
}
