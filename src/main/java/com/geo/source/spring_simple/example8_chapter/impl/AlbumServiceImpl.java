package com.geo.source.spring_simple.example8_chapter.impl;

import com.geo.source.spring_simple.example8_chapter.repository.AlbumRepository;
import com.geo.source.spring_simple.example8_chapter.AlbumService;
import com.geo.source.spring_simple.example8_chapter.entity.Album;
import com.geo.source.spring_simple.example8_chapter.entity.Singer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * JpaRepository 测试
 *
 * @author YanZhen
 * @since 2019-06-21 18:17
 */
@Service
public class AlbumServiceImpl implements AlbumService {

  @Resource
  private AlbumRepository albumRepository;

  @Override
  public List<Album> findBySinger(Singer singer) {
    return albumRepository.findBySinger(singer);
  }

  @Override
  public List<Album> findByTitle(String title) {
    return albumRepository.findByTitle(title);
  }
}
