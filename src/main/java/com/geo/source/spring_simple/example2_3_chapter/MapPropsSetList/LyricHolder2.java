package com.geo.source.spring_simple.example2_3_chapter.MapPropsSetList;

import org.springframework.stereotype.Service;

/**
 * 第二个测试对象
 *
 * @author YanZhen
 * @since 2019-05-17 12:47
 */
@Service
public class LyricHolder2 implements ContentHolder {
  private String value = "'You be the DJ, I'll be the driver'";

  @Override
  public String toString() {
    return "LyricHolder : { " + value + " }";
  }
}
