package com.geo.source.spring_simple.example9_chapter.Impl;

import com.geo.source.spring_simple.example9_chapter.repository.SingerRepository;
import com.geo.source.spring_simple.example9_chapter.SingerService;
import com.geo.source.spring_simple.example9_chapter.entity.Singer;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 事务管理
 *
 * @author YanZhen
 * @since 2019-07-02 14:42
 */
@Service
public class SingerServiceImpl implements SingerService {
  private SingerRepository singerRepository;

  @Resource
  public void setSingerRepository(SingerRepository singerRepository) {
    this.singerRepository = singerRepository;
    System.out.println("Repository init over!");
  }

  @Override
  public List<Singer> findAll() {
    return Lists.newArrayList(singerRepository.findAll());
  }
}
