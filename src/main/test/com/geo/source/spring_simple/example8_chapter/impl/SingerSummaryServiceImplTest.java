package com.geo.source.spring_simple.example8_chapter.impl;

import com.geo.source.spring_simple.example8_chapter.JpaConfig;
import com.geo.source.spring_simple.example8_chapter.SingerSummaryService;
import com.geo.source.spring_simple.example8_chapter.entity.SingerSummary;
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
 * jpa2 测试，使用构造函数表达式构造结果类型
 *
 * @author YanZhen
 * @since 2019-06-19 10:22
 */
class SingerSummaryServiceImplTest {

  private Logger logger = LoggerFactory.getLogger(SingerSummaryServiceImplTest.class);

  private GenericApplicationContext context;
  private SingerSummaryService singerSummaryService;

  @BeforeEach
  void setUp() {
    context = new AnnotationConfigApplicationContext(JpaConfig.class);
    singerSummaryService = context.getBean("singerSummaryServiceImpl", SingerSummaryService.class);
    Assertions.assertNotNull(singerSummaryService);
  }

  @AfterEach
  void tearDown() {
    if (context != null) {
      context.close();
    }
  }

  @Test
  void findAll() {
    final List<SingerSummary> singerSummaries = singerSummaryService.findAll();
    Assertions.assertNotNull(singerSummaries);
    // Assertions.assertEquals(4, singerSummaries.size());
    singerSummaries.forEach(s -> logger.info(s.toString()));
  }
}