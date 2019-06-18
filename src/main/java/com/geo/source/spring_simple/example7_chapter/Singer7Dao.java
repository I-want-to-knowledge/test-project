package com.geo.source.spring_simple.example7_chapter;

import com.geo.source.spring_simple.example7_chapter.entity.Singer;

import java.util.List;

/**
 * hibernate框架，查询接口
 *
 * @author YanZhen
 * @since 2019-06-17 14:19
 */
public interface Singer7Dao {
  List<Singer> findAll();

  List<Singer> findAllWithAlbum();

  Singer findById(Long id);

  Singer save(Singer singer);

  void delete(Singer singer);
}
