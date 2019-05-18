package com.geo.source.spring_simple.example2.lookup;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 普通pojo 例2
 * spring的查找方法注入
 *
 * @author YanZhen
 * @since 2019-05-18 13:03
 */
@Component
@Scope("prototype")// 设置'prototype'保证每次获取bean都会有一个新的实例生成，默认是单例'singleton'
class Singer2 {
  private String lyric = "I played a quick game of chess with the salt and pepper shaker";

  void sing() {
    //System.out.println(lyric);
  }
}
