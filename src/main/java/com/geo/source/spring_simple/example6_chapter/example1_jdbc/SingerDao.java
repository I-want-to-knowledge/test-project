package com.geo.source.spring_simple.example6_chapter.example1_jdbc;

import com.geo.source.spring_simple.example6_chapter.entity1.Album;
import com.geo.source.spring_simple.example6_chapter.entity1.Singer;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

/**
 * 歌手数据访问对象
 *
 * @author YanZhen
 * @since 2019-06-11 14:42
 */
public interface SingerDao {
  List<Singer> findAll();

  List<Singer> findByFirstName(String firstName);

  String findLastNameById(Long id);

  String findFirstNameById(Long id);

  void insert(Singer singer);

  void update(Singer singer);

  void delete(Long singerId);

  List<Singer> findAllWithDetail();

  void insertWithDetail(Singer singer);

  default String findNameById(Long id) {
    throw new NotImplementedException("findNameById has not been implemented!");
  }

  /**
   * 结果集提取器 测试
   * @return 结果
   */
  default List<Singer> findAllWithAlbums() {
    throw new NotImplementedException("findAllWithAlbums has not been implemented!");
  }

  /**
   * BatchSqlUpdate 批量处理
   * @return 结果
   */
  default void insertWithAlbums(Singer singer) {
    throw new NotImplementedException("insertWithAlbums has not been implemented!");
  }
}
