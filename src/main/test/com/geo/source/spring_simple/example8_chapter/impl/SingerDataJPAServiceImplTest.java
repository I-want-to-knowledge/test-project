package com.geo.source.spring_simple.example8_chapter.impl;

import com.geo.source.spring_simple.example8_chapter.JpaConfig;
import com.geo.source.spring_simple.example8_chapter.SingerDataJPAService;
import com.geo.source.spring_simple.example8_chapter.entity.Singer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

/**
 * Spring Date JPA CrudRepository扩展进行数据库操作，测试
 *
 * @author YanZhen
 * @since 2019-06-21 16:55
 */
class SingerDataJPAServiceImplTest {
  private Logger logger = LoggerFactory.getLogger(SingerDataJPAServiceImplTest.class);

  private GenericApplicationContext context;
  private SingerDataJPAService singerDataJPAService;

  @BeforeEach
  void setUp() {
    context = new AnnotationConfigApplicationContext(JpaConfig.class);
    singerDataJPAService = context.getBean("singerDataJPAServiceImpl", SingerDataJPAService.class);
    Assertions.assertNotNull(singerDataJPAService);
  }

  @AfterEach
  void tearDown() {
    context.close();
  }

  @Test
  void findAll() {
    final List<Singer> singers = singerDataJPAService.findAll();
    Assertions.assertEquals(singers.size(), 4);
    singers.forEach(s->logger.info(s.toString()));
  }

  @Test
  void findByFirstName() {
    final List<Singer> singers = singerDataJPAService.findByFirstName("y");
    Assertions.assertEquals(singers.size(), 1);
    logger.info(singers.get(0).toString());
  }

  @Test
  void findByFirstNameAndLastName() {
    final List<Singer> singers = singerDataJPAService.findByFirstNameAndLastName("y", "z");
    Assertions.assertEquals(singers.size(), 1);
    logger.info(singers.get(0).toString());
  }
}