package com.geo.source.spring_simple.example2.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * autowired 注入
 *
 * @author YanZhen
 * @since 2019-05-16 16:13
 */
@Service
class Singer {

  @Autowired
  private Inspiration inspiration;

  void sing() {
    System.out.println("... " + inspiration.getLyric());
  }
}
