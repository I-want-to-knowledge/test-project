package com.geo.source.spring_simple.example9_chapter;

import com.geo.source.spring_simple.example9_chapter.entity.Singer;

import java.util.List;

/**
 * 事务测试
 *
 * @author YanZhen
 * @since 2019-07-02 14:39
 */
public interface SingerService {
  List<Singer> findAll();
}
