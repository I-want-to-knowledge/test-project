package com.geo.source.spring_simple.example8_chapter.impl;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 操作类型
 *
 * @author YanZhen
 * @since 2019-06-25 11:37
 */
@Component
public class AuditorAwareBean implements AuditorAware<String> {

  @Override
  public Optional<String> getCurrentAuditor() {
    return Optional.of("example8_chapter");
  }
}
