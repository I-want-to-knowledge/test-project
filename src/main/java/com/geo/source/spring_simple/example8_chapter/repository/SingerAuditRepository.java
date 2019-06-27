package com.geo.source.spring_simple.example8_chapter.repository;

import com.geo.source.spring_simple.example8_chapter.entity.SingerAudit;
import org.springframework.data.repository.CrudRepository;

/**
 * 继承增删改查库（CrudRepository），
 *
 * @author YanZhen
 * @since 2019-06-25 10:04
 */
public interface SingerAuditRepository extends CrudRepository<SingerAudit, Long> {

}
