package com.geo.source.spring_simple.example8_chapter.impl;

import com.geo.source.spring_simple.example7_chapter.entity.Singer;
import com.geo.source.spring_simple.example8_chapter.SingerService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * jpa 2 数据访问
 *
 * @author YanZhen
 * @since 2019-06-18 16:06
 */
@Service
@Repository
public class SingerServiceImpl implements SingerService {

  @PersistenceContext
  private EntityManager emf;

  @Override
  public List<Singer> findAll() {
    return null;
  }

  @Override
  public List<Singer> findAllWithAlbum() {
    return null;
  }

  @Override
  public Singer findById(Long id) {
    return null;
  }

  @Transactional
  @Override
  public Singer save(Singer singer) {
    return null;
  }

  @Transactional
  @Override
  public void delete(Singer singer) {

  }

  @Override
  public List<Singer> findAllByNativeQuery() {
    return null;
  }
}
