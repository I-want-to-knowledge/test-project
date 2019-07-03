package com.geo.source.spring_simple.example9_chapter.Impl;

import com.geo.source.spring_simple.example9_chapter.ServicesConfig;
import com.geo.source.spring_simple.example9_chapter.SingerService;
import com.geo.source.spring_simple.example9_chapter.entity.Singer;
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
 * spring的事务测试
 *
 * @author YanZhen
 * @since 2019-07-02 16:43
 */
class SingerServiceImplTest {
  private Logger logger = LoggerFactory.getLogger(SingerServiceImplTest.class);
  private GenericApplicationContext context;
  private SingerService singerService;

  @BeforeEach
  void setUp() {
    context = new AnnotationConfigApplicationContext(ServicesConfig.class);
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
    singers.forEach(singer -> logger.info(singer.toString()));
  }
}