package com.geo.source.spring_simple.example8_chapter;

import com.geo.source.spring_simple.example7_chapter.entity.Singer;

import java.util.List;

/**
 * jpa 2 数据库操作
 *
 * @author YanZhen
 * @since 2019-06-18 16:01
 */
public interface SingerService {
  List<Singer> findAll();

  List<Singer> findAllWithAlbum();

  Singer findById(Long id);

  Singer save(Singer singer);

  void delete(Singer singer);

  List<Singer> findAllByNativeQuery();
}
