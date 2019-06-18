package com.geo.source.spring_simple.example7_chapter.entity;

import com.geo.source.spring_simple.example7_chapter.APPConfig;
import com.geo.source.spring_simple.example7_chapter.Singer7Dao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.time.LocalDate;
import java.util.List;

/**
 * jpa 数据访问，测试
 *
 * @author YanZhen
 * @since 2019-06-17 15:40
 */
class Singer7DaoImplTest {
  private static Logger logger = LoggerFactory.getLogger(Singer7DaoImplTest.class);
  private GenericApplicationContext context;
  private Singer7Dao singer7Dao;

  @BeforeEach
  void setUp() {
    context = new AnnotationConfigApplicationContext(APPConfig.class);
    singer7Dao = context.getBean("singer7DaoImpl", Singer7Dao.class);
    Assertions.assertNotNull(singer7Dao);
  }

  @AfterEach
  void tearDown() {
    context.close();
  }

  @Test
  void findAll() {
    // singer7Dao.delete();
    final List<Singer> singers = singer7Dao.findAll();
    singers.forEach(singer -> logger.info(singer.toString()));// LazyInitializationException 异常
  }

  @Test
  void findAllWithAlbum() {
    final List<Singer> singers = singer7Dao.findAllWithAlbum();
    Assertions.assertEquals(4, singers.size());
    singers.forEach(singer -> logger.info(singer.toString()));
  }

  @Test
  void findById() {
    final Singer singer = singer7Dao.findById(1L);
    Assertions.assertNotNull(singer);
    logger.info(singer.toString());
  }

  @Test
  void insert() {
    final Singer singer = new Singer();
    singer.setFirstName("BB");
    singer.setLastName("King");
    singer.setBirthDate(LocalDate.of(1940, 8, 16));
    singer.addAlbum(new Album(null, "My Kind of Blues", LocalDate.of(1961, 7, 18)));
    singer.addAlbum(new Album(null, "A Heart Full of Blues", LocalDate.of(1962, 3, 20)));

    singer7Dao.save(singer);
    Assertions.assertNotNull(singer.getId());

    final List<Singer> singers = singer7Dao.findAllWithAlbum();
    singers.forEach(s -> logger.info(s.toString()));
  }

  @Test
  void update() {
    final Singer singer1 = singer7Dao.findById(4L);
    Assertions.assertNotNull(singer1);
    Assertions.assertEquals("y", singer1.getFirstName());

    final Singer singer = new Singer();
    singer.setId(4L);
    singer.setFirstName("y1");
    singer.setLastName("z1");
    singer.setBirthDate(LocalDate.now());
    singer.addAlbum(new Album(4L, "第二张了", LocalDate.now()));
    singer7Dao.save(singer);
    Assertions.assertNotNull(singer.getId());

    final List<Singer> singers = singer7Dao.findAllWithAlbum();
    singers.forEach(s -> logger.info(s.toString()));
  }

  @Test
  void deleted() {
    final Singer singer = singer7Dao.findById(4L);
    Assertions.assertNotNull(singer);
    Assertions.assertEquals("y", singer.getFirstName());

    singer7Dao.delete(singer);

    final List<Singer> singers = singer7Dao.findAllWithAlbum();
    singers.forEach(s -> logger.info(s.toString()));
  }
}