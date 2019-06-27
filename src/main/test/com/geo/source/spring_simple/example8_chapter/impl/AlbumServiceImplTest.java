package com.geo.source.spring_simple.example8_chapter.impl;

import com.geo.source.spring_simple.example8_chapter.AlbumService;
import com.geo.source.spring_simple.example8_chapter.JpaConfig;
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

import java.util.List;

/**
 * JpaRepository测试
 *
 * @author YanZhen
 * @since 2019-06-21 18:20
 */
class AlbumServiceImplTest {
  private Logger logger = LoggerFactory.getLogger(AlbumServiceImplTest.class);

  private GenericApplicationContext context;
  private AlbumService albumService;

  @BeforeEach
  void setUp() {
    context = new AnnotationConfigApplicationContext(JpaConfig.class);
    albumService = context.getBean("albumServiceImpl", AlbumService.class);
    Assertions.assertNotNull(albumService);
  }

  @AfterEach
  void tearDown() {
    context.close();
  }

  @Test
  void findBySinger() {
    final Singer singer = new Singer();
    singer.setId(4L);

    final List<Album> albums = albumService.findBySinger(singer);
    Assertions.assertEquals(1, albums.size());
    logger.info(albums.get(0).toString());
  }

  @Test
  void findByTitle() {
    final List<Album> albums = albumService.findByTitle("The");
    albums.forEach(a->logger.info(a.getSinger().toString()));
  }
}