package com.geo.source.spring_simple.example8_chapter.impl;

import com.geo.source.spring_simple.example8_chapter.JpaConfig;
import com.geo.source.spring_simple.example8_chapter.SingerAuditService;
import com.geo.source.spring_simple.example8_chapter.entity.SingerAudit;
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
 * Auditable接口测试
 *
 * @author YanZhen
 * @since 2019-06-25 11:44
 */
class SingerAuditServiceImplTest {
  private Logger logger = LoggerFactory.getLogger(SingerAuditServiceImplTest.class);
  private GenericApplicationContext context;
  private SingerAuditService singerAuditService;

  @BeforeEach
  void setUp() {
    context = new AnnotationConfigApplicationContext(JpaConfig.class);
    singerAuditService = context.getBean("singerAuditServiceImpl", SingerAuditService.class);
  }

  @AfterEach
  void tearDown() {
    context.close();
  }

  @Test
  void findAll() {
    final List<SingerAudit> singerAudits = singerAuditService.findAll();
    singerAudits.forEach(singerAudit -> logger.info(singerAudit.toString()));

    save();
  }

  // @Test
  void findById(Long id) {
    final SingerAudit singerAudit = singerAuditService.findById(id);
    Assertions.assertEquals(singerAudit.getFirstName(), "BB");
    logger.info("新添加数据为=========={}", singerAudit.toString());
    try {
      Thread.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    singerAudit.setFirstName("John Clayton");
    singerAuditService.save(singerAudit);
    Assertions.assertEquals(singerAudit.getFirstName(), "John Clayton");
    logger.info("修改后========{}", singerAudit);
  }

  // @Test
  void save() {
    logger.info("Add new singer");
    final SingerAudit singerAudit = new SingerAudit();
    singerAudit.setFirstName("BB");
    singerAudit.setLastName("King");
    singerAudit.setBirthDate(LocalDate.of(1940, 8, 16));
    singerAuditService.save(singerAudit);

    final List<SingerAudit> singerAudits = singerAuditService.findAll();
    singerAudits.forEach(singerAudit1 -> logger.info(singerAudit1.toString()));

    findById(singerAudit.getId());
  }
}