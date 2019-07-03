package com.geo.source.spring_simple.example9_chapter.repository;

import com.geo.source.spring_simple.example9_chapter.entity.Singer;
import org.springframework.data.repository.CrudRepository;

/**
 * 事务测试
 *
 * @author YanZhen
 * @since 2019-07-02 14:45
 */
public interface SingerRepository extends CrudRepository<Singer, Long> {

}
