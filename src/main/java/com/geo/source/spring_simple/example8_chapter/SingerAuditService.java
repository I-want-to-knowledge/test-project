package com.geo.source.spring_simple.example8_chapter;

import com.geo.source.spring_simple.example8_chapter.entity.SingerAudit;

import java.util.List;

/**
 * Auditable 接口连接测试
 *
 * @author YanZhen
 * @since 2019-06-25 09:54
 */
public interface SingerAuditService {
  List<SingerAudit> findAll();

  SingerAudit findById(Long id);

  SingerAudit save(SingerAudit singerAudit);
}
