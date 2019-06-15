package com.geo.source.spring_simple.example6_chapter.example1_jdbc;

import com.geo.source.spring_simple.example6_chapter.entity1.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

/**
 * jdbc 测试
 *
 * @author YanZhen
 * @since 2019-06-11 17:47
 */
public class PlainJDBCDemo {
  private static Logger logger = LoggerFactory.getLogger(PlainJDBCDemo.class);
  private static SingerDao singerDao = new PlainSingerDaoImpl();
  public static void main(String[] args) {
    logger.info("Listing initial singer data:");
    listAllSingers();

    logger.info("---------------");
    logger.info("Insert a new singer");

    final Singer singer = new Singer();
    singer.setFirstName("Ed");
    singer.setLastName("Sheela");
    singer.setBirthDate(LocalDate.of(1991, 2, 19));
    singerDao.insert(singer);

    logger.info("Listing singer data after new singer created:");
    listAllSingers();

    logger.info("----------------------");
    logger.info("Deleting the previous created singer");

    singerDao.delete(singer.getId());

    logger.info("Listing singer data new singer deleted:");
    listAllSingers();
  }

  private static void listAllSingers() {
    final List<Singer> all = singerDao.findAll();
    all.forEach(singer -> logger.info(singer.toString()));
  }
}
