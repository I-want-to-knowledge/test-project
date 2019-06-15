package com.geo.source.spring_simple.example6_chapter.example3_embedded_database;

import com.geo.source.spring_simple.example6_chapter.entity1.Singer;
import com.geo.source.spring_simple.example6_chapter.example1_jdbc.SingerDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

/**
 * 嵌入式数据库，测试
 *
 * @author YanZhen
 * @since 2019-06-13 09:56
 */
class EmbeddedJDBC2ConfigTest {
  @Test
  void testOne() {
    final GenericApplicationContext context = new AnnotationConfigApplicationContext(EmbeddedJDBC2Config.class);
    final SingerDao singerDao = context.getBean(SingerDao.class);
    Assertions.assertNotNull(singerDao);
    final String singerName = singerDao.findNameById(2L);
    Assertions.assertEquals("John Mayer", singerName);
    context.close();
  }

  @Test
  void testRowMapper() {
    final GenericApplicationContext context = new AnnotationConfigApplicationContext(EmbeddedJDBC2Config.class);
    final SingerDao singerDao = context.getBean(SingerDao.class);
    Assertions.assertNotNull(singerDao);
    final List<Singer> singers = singerDao.findAll();
    Assertions.assertEquals(4, singers.size());
    singers.forEach(System.out::println);
    context.close();
  }

  /**
   * 结果集提取器 测试
   */
  @Test
  void testResultSetExtractor() {
    final GenericApplicationContext context = new AnnotationConfigApplicationContext(EmbeddedJDBC2Config.class);
    final SingerDao singerDao = context.getBean(SingerDao.class);
    Assertions.assertNotNull(singerDao);
    final List<Singer> singers = singerDao.findAllWithAlbums();
    Assertions.assertEquals(4, singers.size());
    singers.forEach(System.out::println);
    context.close();
  }
}