package com.geo.source.spring_simple.example8_chapter.impl;

import com.geo.source.spring_simple.example8_chapter.JpaConfig;
import com.geo.source.spring_simple.example8_chapter.SingerService;
import com.geo.source.spring_simple.example8_chapter.entity.Album;
import com.geo.source.spring_simple.example8_chapter.entity.Singer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

/**
 * jpa2 数据操作测试
 *
 * @author YanZhen
 * @since 2019-06-18 16:34
 */
class SingerServiceImplTest {
  Logger logger = LoggerFactory.getLogger(SingerServiceImplTest.class);
  GenericApplicationContext context;
  SingerService singerService;

  @BeforeEach
  void setUp() {
    context = new AnnotationConfigApplicationContext(JpaConfig.class);
    singerService = context.getBean("singerServiceImpl", SingerService.class);
    Assertions.assertNotNull(singerService);
  }

  @AfterEach
  void tearDown() {
    context.close();
  }

  @Test
  void findAll() {
    final List<Singer> singers = singerService.findAll();
    Assertions.assertEquals(4, singers.size());
    // singers.forEach(s -> logger.info(s.toString()));
  }

  @Test
  void findAllWithAlbum() {
    final List<Singer> singers = singerService.findAllWithAlbum();
    Assertions.assertEquals(4, singers.size());
    singers.forEach(s -> logger.info(s.toString()));
  }

  @Test
  void findById() {
    final Singer singer = singerService.findById(4L);
    Assertions.assertNotNull(singer);
    Assertions.assertEquals("y", singer.getFirstName());
    logger.info(singer.toString());
  }

  @Test
  void save() {
    final Singer singer = new Singer();
    singer.setFirstName("BB");
    singer.setLastName("King");
    singer.setBirthDate(LocalDate.of(1940, 8, 16));
    singer.addAlbum(new Album(null, "My kind of Blues", LocalDate.of(1961, 7, 18)));
    singer.addAlbum(new Album(null, "A Heart Full of Blues", LocalDate.of(1962, 3, 20)));
    singerService.save(singer);
    Assertions.assertNotNull(singer.getId());

    final List<Singer> singers = singerService.findAllWithAlbum();
    singers.forEach(s -> logger.info(s.toString()));
  }

  @Test
  void update() {
    final Singer singer = singerService.findById(4L);
    Assertions.assertNotNull(singer);
    logger.info(singer.toString());

    singer.setFirstName("y1");
    singer.setLastName("z1");
    singer.removeAlbum(singer.getAlbums().stream().filter(album -> "第一张唱片！".equals(album.getTitle())).findFirst().orElse(null));
    singer.addAlbum(new Album(4L, "第二张", LocalDate.now()));

    final Singer save = singerService.save(singer);
    Assertions.assertNotNull(singer.getId());

    final List<Singer> singers = singerService.findAllWithAlbum();
    singers.forEach(s -> logger.info(s.toString()));
  }

  @Test
  void delete() {
    final Singer singer = singerService.findById(4L);
    Assertions.assertNotNull(singer);
    Assertions.assertEquals("y", singer.getFirstName());
    singerService.delete(singer);

    final List<Singer> singers = singerService.findAllWithAlbum();
    singers.forEach(s -> logger.info(s.toString()));
  }

  @Test
  void findAllByNativeQuery() {
  }

  @Test
  void displayAllSingerSummary() {
    final List singers = singerService.displayAllSingerSummary();
    Assertions.assertNotNull(singers);
    int count = 0;
    for (Iterator i = singers.iterator(); i.hasNext(); ) {
      final Object[] os = (Object[]) i.next();
      logger.info("{}: {}, {}, {}, {}", count++, os[0], os[1], os[2], os[3]);
    }

    singers.forEach(s -> {
      final Object[] os = (Object[]) s;
      logger.info(": {}, {}, {}, {}", os[0], os[1], os[2], os[3]);
    });
  }

  @Test
  void findByCriteriaQuery() {
    final List<Singer> byCriteriaQuery = singerService.findByCriteriaQuery("y", "z");
    Assertions.assertEquals(1, byCriteriaQuery.size());

    byCriteriaQuery.forEach(s->logger.info(s.toString()));
  }
}