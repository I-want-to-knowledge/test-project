package com.geo.source.spring_simple.example8_chapter.impl;

import com.geo.source.spring_simple.example8_chapter.SingerAuditService;
import com.geo.source.spring_simple.example8_chapter.entity.SingerAudit;
import com.geo.source.spring_simple.example8_chapter.repository.SingerAuditRepository;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * Auditable接口测试
 *
 * @author YanZhen
 * @since 2019-06-25 09:58
 */
@Service
public class SingerAuditServiceImpl implements SingerAuditService {
  @Resource
  private SingerAuditRepository singerAuditRepository;

  @Override
  public List<SingerAudit> findAll() {
    return Lists.newArrayList(singerAuditRepository.findAll());
  }

  @Override
  public SingerAudit findById(Long id) {
    final Optional<SingerAudit> singerAuditRepositoryById = singerAuditRepository.findById(id);
    return singerAuditRepositoryById.orElse(new SingerAudit());
  }

  @Transactional
  @Override
  public SingerAudit save(SingerAudit singerAudit) {
    return singerAuditRepository.save(singerAudit);
  }
}
