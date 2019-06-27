package com.geo.source.spring_simple.example8_chapter.repository;

import com.geo.source.spring_simple.example8_chapter.entity.Album;
import com.geo.source.spring_simple.example8_chapter.entity.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Date JPA JpaRepository扩展进行数据库操作，实现类
 *
 * @author YanZhen
 * @since 2019-06-21 18:02
 */
public interface AlbumRepository extends JpaRepository<Album, Long> {
  List<Album> findBySinger(Singer singer);

  @Query("select a from Album a where a.title like %:title%")
  List<Album> findByTitle(@Param("title") String t);// 参数名为title时@Param注解可以取消
}
