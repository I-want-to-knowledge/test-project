package com.geo.source.spring_simple.example8_chapter;

import com.geo.source.spring_simple.example8_chapter.entity.Singer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * spring data jpa中repository接口的扩展
 *
 * @author YanZhen
 * @since 2019-06-20 15:42
 */
public interface SingerRepository extends CrudRepository<Singer, Long> {
  List<Singer> findByFirstName(String firstName);
  List<Singer> findByFirstNameAndLastName(String firstName, String lastName);
}
