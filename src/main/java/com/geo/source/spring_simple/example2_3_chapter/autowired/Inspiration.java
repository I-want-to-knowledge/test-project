package com.geo.source.spring_simple.example2_3_chapter.autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * autowired 注解
 *
 * @author YanZhen
 * @since 2019-05-16 16:07
 */
@Component
public class Inspiration {

  private String lyric = "test";

  public Inspiration(@Value("从新赋值后！") String lyric) {
    this.lyric = lyric;
  }

  public String getLyric() {
    return lyric;
  }

  public void setLyric(String lyric) {
    this.lyric = lyric;
  }
}
