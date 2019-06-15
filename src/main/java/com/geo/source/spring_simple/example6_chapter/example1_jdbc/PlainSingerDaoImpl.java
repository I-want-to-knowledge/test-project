package com.geo.source.spring_simple.example6_chapter.example1_jdbc;

import com.geo.source.spring_simple.example6_chapter.entity1.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 歌唱数据实现
 *
 * @author YanZhen
 * @since 2019-06-11 15:19
 */
public class PlainSingerDaoImpl implements SingerDao {

  private static Logger logger = LoggerFactory.getLogger(PlainSingerDaoImpl.class);

  static {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      logger.error("Problem loading DB dDiver!", e);
    }
  }

  private Connection getConnection() throws SQLException {
    return DriverManager.getConnection("jdbc:mysql://192.168.10.166:3306/spring_jdbc_test?useSSL=false",
            "yz", "123456");
  }

  private void closeConnection(Connection connection) {
    if (connection == null) {
      return;
    }
    try {
      connection.close();
    } catch (SQLException e) {
      logger.error("Problem closing connection to the database!", e);
    }
  }

  @Override
  public List<Singer> findAll() {
    List<Singer> singers = new ArrayList<>();
    Connection connection = null;
    try {
      connection = getConnection();
      final PreparedStatement statement = connection.prepareStatement("SELECT * FROM singer");
      final ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        final Singer singer = new Singer();
        singer.setId(resultSet.getLong("id"));
        singer.setFirstName(resultSet.getString("first_name"));
        singer.setLastName(resultSet.getString("last_name"));
        singer.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
        singers.add(singer);
      }
      statement.close();
    } catch (SQLException e) {
      logger.error("Problem when executing SELECT!", e);
    } finally {
      closeConnection(connection);
    }
    return singers;
  }

  @Override
  public List<Singer> findByFirstName(String firstName) {
    return null;
  }

  @Override
  public String findLastNameById(Long id) {
    return null;
  }

  @Override
  public String findFirstNameById(Long id) {
    return null;
  }

  @Override
  public void insert(Singer singer) {
    Connection connection = null;
    try {
      connection = getConnection();
      final PreparedStatement statement = connection.prepareStatement(
              "insert into singer (first_name, last_name, birth_date) VALUES (?,?,?)",
              Statement.RETURN_GENERATED_KEYS);
      statement.setString(1, singer.getFirstName());
      statement.setString(2, singer.getLastName());
      statement.setDate(3, Date.valueOf(singer.getBirthDate()));
      statement.execute();
      final ResultSet keys = statement.getGeneratedKeys();
      if (keys.next()) {
        singer.setId(keys.getLong(1));
      }
      statement.close();
    } catch (SQLException e) {
      logger.error("Problem executing INSERT", e);
    } finally {
      closeConnection(connection);
    }
  }

  @Override
  public void update(Singer singer) {
    Connection connection = null;
    try {
      connection = getConnection();
      final PreparedStatement statement = connection.prepareStatement(
              "update singer set first_name=?,last_name=?,birth_date=? where id=?");
      statement.setString(1, singer.getFirstName());
      statement.setString(2, singer.getLastName());
      statement.setDate(3, Date.valueOf(singer.getBirthDate()));
      statement.setLong(4, singer.getId());
      statement.execute();
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeConnection(connection);
    }
  }

  @Override
  public void delete(Long singerId) {
    Connection connection = null;
    try {
      connection = getConnection();
      final PreparedStatement statement = connection.prepareStatement("delete from singer where id=?;");
      statement.setLong(1, singerId);
      statement.execute();
      statement.close();
    } catch (SQLException e) {
      logger.error("Problem executing DELETE!", e);
    } finally {
      closeConnection(connection);
    }
  }

  @Override
  public List<Singer> findAllWithDetail() {
    return null;
  }

  @Override
  public void insertWithDetail(Singer singer) {

  }
}
