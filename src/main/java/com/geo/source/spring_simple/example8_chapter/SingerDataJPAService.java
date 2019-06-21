package com.geo.source.spring_simple.example8_chapter;

import com.geo.source.spring_simple.example8_chapter.entity.Singer;

import java.util.List;

/**
 * Spring Date JPA repository扩展进行数据库操作
 *
 * @author YanZhen
 * @since 2019-06-20 15:37
 */
public interface SingerDataJPAService {
  List<Singer> findAll();
  List<Singer> findByFirstName(String firstName);
  List<Singer> findByFirstNameAndLastName(String firstName, String lastName);
}
