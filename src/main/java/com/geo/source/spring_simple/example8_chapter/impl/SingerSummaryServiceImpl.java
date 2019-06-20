package com.geo.source.spring_simple.example8_chapter.impl;

import com.geo.source.spring_simple.example8_chapter.SingerSummaryService;
import com.geo.source.spring_simple.example8_chapter.entity.SingerSummary;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * 歌手摘要信息服务
 *
 * @author YanZhen
 * @since 2019-06-19 10:10
 */
@Service
public class SingerSummaryServiceImpl implements SingerSummaryService {
  @PersistenceContext
  private EntityManager em;

  @Override
  public List<SingerSummary> findAll() {
    return em.createQuery("select new com.geo.source.spring_simple.example8_chapter.entity.SingerSummary(" +
                    "s.firstName, s.lastName, a.title) from Singer s left join s.albums a where a.releaseDate=" +
                    "(select max(a2.releaseDate) from Album a2 where a2.singer.id = s.id)",
            SingerSummary.class).getResultList();
  }
}
