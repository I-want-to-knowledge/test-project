package com.geo.source.spring_simple.example2_3_chapter.depends_on;

import org.springframework.stereotype.Component;

/**
 * 依赖项，pojo测试类
 *
 * @author YanZhen
 * @since 2019-05-20 17:31
 */
@Component
public class Guiter {
  void sing() {
    System.out.println("Cm Ed Fm Ab Bb");
  }
}
