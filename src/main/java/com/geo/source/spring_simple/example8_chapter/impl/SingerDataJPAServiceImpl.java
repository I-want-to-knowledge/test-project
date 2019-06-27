package com.geo.source.spring_simple.example8_chapter.impl;

import com.geo.source.spring_simple.example8_chapter.SingerDataJPAService;
import com.geo.source.spring_simple.example8_chapter.repository.SingerRepository;
import com.geo.source.spring_simple.example8_chapter.entity.Singer;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Spring Date JPA CrudRepository扩展进行数据库操作，实现类
 *
 * @author YanZhen
 * @since 2019-06-20 15:45
 */
@Service
public class SingerDataJPAServiceImpl implements SingerDataJPAService {

  @Resource
  private SingerRepository singerRepository;

  @Override
  public List<Singer> findAll() {
    return Lists.newArrayList(singerRepository.findAll());
  }

  @Override
  public List<Singer> findByFirstName(String firstName) {
    return singerRepository.findByFirstName(firstName);
  }

  @Override
  public List<Singer> findByFirstNameAndLastName(String firstName, String lastName) {
    return singerRepository.findByFirstNameAndLastName(firstName, lastName);
  }
}
