package com.geo.source.spring_simple.example6_chapter.example4_spring_jdbc_annotation;

import com.geo.source.spring_simple.example6_chapter.entity1.Album;
import com.geo.source.spring_simple.example6_chapter.entity1.Singer;
import com.geo.source.spring_simple.example6_chapter.example1_jdbc.SingerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * jdbc 注解 测试
 *
 * @author YanZhen
 * @since 2019-06-13 14:27
 */
@Repository("singerDao")
public class JDBCSingerDaoImpl implements SingerDao {

  private static final Logger logger = LoggerFactory.getLogger(JDBCSingerDaoImpl.class);
  private DataSource dataSource;
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Resource
  public void setDataSource(DataSource dataSource) {
    namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    this.dataSource = dataSource;
  }

  public DataSource getDataSource() {
    return dataSource;
  }

  private final ResultSetExtractor<List<Singer>> resultSetExtractor = rs -> {
    Map<Long, Singer> map = new HashMap<>();
    Singer singer;
    while (rs.next()) {
      final long singerId = rs.getLong("id");
      singer = map.get(singerId);
      if (singer == null) {
        singer = new Singer();
        singer.setId(singerId);
        singer.setFirstName(rs.getString("first_name"));
        singer.setLastName(rs.getString("last_name"));
        singer.setBirthDate(rs.getDate("birth_date").toLocalDate());
        map.put(singerId, singer);
      }

      final long albumId = rs.getLong("album_id");
      if (albumId > 0) {
        Album album = new Album();
        album.setId(albumId);
        album.setSingerId(singerId);
        album.setTitle(rs.getString("title"));
        album.setReleaseDate(rs.getDate("release_date").toLocalDate());
        singer.addAlbum(album);
      }
    }
    return new ArrayList<>(map.values());
  };

  @Override
  public List<Singer> findAll() {
    return namedParameterJdbcTemplate.query("select s.id,s.first_name,s.last_name,s.birth_date," +
            "a.id as album_id,a.singer_id,a.title,a.release_date" +
            " from singer s left join album a on s.id = a.singer_id",
            resultSetExtractor);
  }

  @Override
  public List<Singer> findByFirstName(String firstName) {
    return null;
  }

  @Override
  public String findLastNameById(Long id) {
    return null;
  }

  @Override
  public String findFirstNameById(Long id) {
    return null;
  }

  @Override
  public void insert(Singer singer) {

  }

  @Override
  public void update(Singer singer) {

  }

  @Override
  public void delete(Long singerId) {

  }

  @Override
  public List<Singer> findAllWithDetail() {
    return null;
  }

  @Override
  public void insertWithDetail(Singer singer) {

  }

  @Override
  public String findNameById(Long id) {
    return null;
  }

  @Override
  public List<Singer> findAllWithAlbums() {
    return null;
  }
}
