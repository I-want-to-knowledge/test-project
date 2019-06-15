package com.geo.source.spring_simple.example6_chapter.example4_spring_jdbc_annotation;

import com.geo.source.spring_simple.example6_chapter.entity1.Album;
import com.geo.source.spring_simple.example6_chapter.entity1.Singer;
import com.geo.source.spring_simple.example6_chapter.example1_jdbc.SingerDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * jdbc注解，测试
 *
 * @author YanZhen
 * @since 2019-06-13 15:08
 */
class JDBCSingerDaoImplTest {
  private Logger logger = LoggerFactory.getLogger(JDBCSingerDaoImplTest.class);

  private SingerDao singerDao;
  private GenericApplicationContext context;

  @BeforeEach
  void setUp() {
    context = new AnnotationConfigApplicationContext(AppConfig.class);
  }

  @AfterEach
  void tearDown() {
    context.close();
  }

  @Test
  void testAnnotation() {
    singerDao = context.getBean("singerDao", SingerDao.class);
    assertNotNull(singerDao);
    final List<Singer> singers = singerDao.findAll();
    singers.forEach(System.out::println);
  }

  /**
   * MappingSqlQuery
   */
  @Test
  void testMappingSqlQuery() {
    singerDao = context.getBean("singerDao2", SingerDao.class);
    assertNotNull(singerDao);
    final List<Singer> singers = singerDao.findAll();
    singers.forEach(singer -> logger.info(singer.toString()));
  }

  /**
   * MappingSqlQuery
   */
  @Test
  void testMappingSqlQuery2() {
    final SingerDao singerDao2 = context.getBean("singerDao2", SingerDao.class);
    assertNotNull(singerDao2);
    final List<Singer> johnInfo = singerDao2.findByFirstName("John");
    johnInfo.forEach(singer -> logger.info(singer.toString()));
  }

  /**
   * SqlUpdate
   */
  @Test
  void testSqlUpdate() {
    final SingerDao singerDao2 = context.getBean("singerDao2", SingerDao.class);
    assertNotNull(singerDao2);
    final Singer singer = new Singer();
    singer.setId(1L);
    singer.setFirstName("yan");
    singer.setLastName("zhen");
    singer.setBirthDate(LocalDate.of(1993, 5, 19));
    singerDao2.update(singer);

    final List<Singer> singers = singerDao2.findAll();
    singers.forEach(singer1 -> logger.info(singer1.toString()));
  }

  /**
   * SqlUpdate
   */
  @Test
  void testInsetSinger() {
    final SingerDao singerDao2 = context.getBean("singerDao2", SingerDao.class);
    assertNotNull(singerDao2);
    final Singer singer = new Singer();
    singer.setFirstName("y2");
    singer.setLastName("z2");
    singer.setBirthDate(LocalDate.of(1993, 5, 19));
    singerDao2.insert(singer);

    assertNotNull("Singer id required!", singer.getId());
    logger.info("主键为：{}", singer.getId());

    final List<Singer> singers = singerDao2.findAll();
    singers.forEach(singer1 -> logger.info(singer1.toString()));
  }

  /**
   * BatchSqlUpdate
   */
  @Test
  void testBatchSqlUpdate() {
    final SingerDao singerDao2 = context.getBean("singerDao2", SingerDao.class);
    assertNotNull(singerDao2);
    Singer singer = new Singer();
    singer.setFirstName("y3");
    singer.setLastName("z3");
    singer.setBirthDate(LocalDate.of(1993, 5, 19));
    for (int i = 0; i < 15; i++) {
      singer.addAlbum(new Album("专辑"+i, LocalDate.of(2000 + i, 1, 1)));
    }

    singerDao2.insertWithAlbums(singer);

    final List<Singer> all = singerDao2.findAllWithAlbums();
    all.forEach(singer1 -> logger.info(singer1.toString()));
  }

  @Test
  void testSqlFunction() {
    final SingerDao singerDao2 = context.getBean("singerDao2", SingerDao.class);
    assertNotNull(singerDao2);
    final String firstNameById = singerDao2.findFirstNameById(3L);
    assertEquals("Eric", firstNameById);
    logger.info("名字：{}", firstNameById);
  }
}