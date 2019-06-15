package com.geo.source.spring_simple.example6_chapter.example3_embedded_database;

import com.geo.source.spring_simple.example.example1.StandardOutMessageRenderer;
import com.geo.source.spring_simple.example6_chapter.example1_jdbc.SingerDao;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 嵌入式数据库 JDBC 测试
 *
 * @author YanZhen
 * @since 2019-06-12 18:21
 */
public class EmbeddedJDBCConfigTest {
  private static Logger logger = LoggerFactory.getLogger(EmbeddedJDBCConfigTest.class);

  @Test
  public void testOne() {
    final GenericApplicationContext context = new AnnotationConfigApplicationContext(EmbeddedJDBCConfig.class);
    testDao(context.getBean(SingerDao.class));
    context.close();
  }

  private void testDao(SingerDao singerDao) {
    Assert.assertNotNull(singerDao);
    final String singerName = singerDao.findNameById(2L);
    logger.info("singerName={}", singerName);
    Assert.assertEquals("John Mayer", singerName);
  }
}