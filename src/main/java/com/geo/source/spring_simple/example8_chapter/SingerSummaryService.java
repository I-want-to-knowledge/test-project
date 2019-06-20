package com.geo.source.spring_simple.example8_chapter;

import com.geo.source.spring_simple.example8_chapter.entity.SingerSummary;

import java.util.List;

/**
 * 歌手摘要信息
 *
 * @author YanZhen
 * @since 2019-06-19 10:08
 */
public interface SingerSummaryService {
  List<SingerSummary> findAll();
}
