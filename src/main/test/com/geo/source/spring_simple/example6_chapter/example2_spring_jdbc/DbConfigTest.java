package com.geo.source.spring_simple.example6_chapter.example2_spring_jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 测试
 *
 * @author YanZhen
 * @since 2019-06-12 12:56
 */
class DbConfigTest {
  private static Logger logger = LoggerFactory.getLogger(DbConfigTest.class);

  @Test
  void testOne() throws SQLException {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext("spring6/drivermanager-cfg-01.xml");
    final DataSource datasource1 = context.getBean("driverManagerDatasource1", DataSource.class);
    Assertions.assertNotNull(datasource1);
    testDataSource(datasource1);
    context.close();
  }

  @Test
  void testTwo() throws SQLException {
    final GenericApplicationContext context = new AnnotationConfigApplicationContext(DbConfig.class);
    final DataSource dataSource = context.getBean("dataSource", DataSource.class);
    Assertions.assertNotNull(dataSource);
    testDataSource(dataSource);
    context.close();
  }

  @Test
  void testThree() throws SQLException {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext("spring6/drivermanager-cfg-02.xml");
    final DataSource datasource2 = context.getBean("driverManagerDatasource2", DataSource.class);
    Assertions.assertNotNull(datasource2);
    testDataSource(datasource2);
    context.close();
  }

  @Test
  void testFour() throws SQLException {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext("spring6/drivermanager-cfg-03.xml");
    final DataSource datasource3 = context.getBean("driverManagerDatasource3", DataSource.class);
    Assertions.assertNotNull(datasource3);
    testDataSource(datasource3);
    context.close();
  }

  private void testDataSource(DataSource dataSource) throws SQLException {
    try (Connection connection = dataSource.getConnection()) {
      final PreparedStatement statement = connection.prepareStatement("SELECT 1");
      final ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        final int anInt = resultSet.getInt(1);
        Assertions.assertEquals(1, anInt);
      }
      statement.close();
    } catch (SQLException e) {
      logger.error("Something unexpected happened.", e);
      throw new SQLException(e);
    }
  }
}