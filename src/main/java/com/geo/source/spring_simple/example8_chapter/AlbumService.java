package com.geo.source.spring_simple.example8_chapter;

import com.geo.source.spring_simple.example8_chapter.entity.Album;
import com.geo.source.spring_simple.example8_chapter.entity.Singer;

import java.util.List;

/**
 * JpaRepository测试
 *
 * @author YanZhen
 * @since 2019-06-21 18:15
 */
public interface AlbumService {
  List<Album> findBySinger(Singer singer);

  List<Album> findByTitle(String title);
}
