package com.geo.source.spring_simple.example6_chapter.example3_embedded_database;

import com.geo.source.spring_simple.example6_chapter.entity1.Album;
import com.geo.source.spring_simple.example6_chapter.entity1.Singer;
import com.geo.source.spring_simple.example6_chapter.example1_jdbc.SingerDao;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * jdbc 数据访问模型 dao实现
 *
 * @author YanZhen
 * @since 2019-06-12 16:53
 */
public class JDBCSinger2Dao implements SingerDao, InitializingBean {

  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  private static final RowMapper<Singer> rowMapper = (rs, rowNum) -> {
    final Singer singer = new Singer();
    singer.setId(rs.getLong("id"));
    singer.setFirstName(rs.getString("first_name"));
    singer.setLastName(rs.getString("last_name"));
    singer.setBirthDate(rs.getDate("birth_date").toLocalDate());
    return singer;
  };

  private static final ResultSetExtractor<List<Singer>> resultSetExtractor = rs -> {
    Singer singer;
    final HashMap<Long, Singer> map = new HashMap<>();
    while (rs.next()) {
      final long id = rs.getLong("id");
      singer = map.get(id);
      if (singer == null) {
        singer = new Singer();
        singer.setId(id);
        singer.setFirstName(rs.getString("first_name"));
        singer.setLastName(rs.getString("last_name"));
        singer.setBirthDate(rs.getDate("birth_date").toLocalDate());
        map.put(id, singer);
      }

      final long albumId = rs.getLong("album_id");
      if (albumId > 0) {
        final Album album = new Album();
        album.setId(albumId);
        album.setSingerId(id);
        album.setTitle(rs.getString("title"));
        album.setReleaseDate(rs.getDate("release_date").toLocalDate());
        singer.addAlbum(album);
      }
    }
    return new ArrayList<>(map.values());
  };

  public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    if (namedParameterJdbcTemplate == null) {
      throw new BeanCreationException("Null NamedParameterJdbcTemplate on SingerDao");
    }
  }

  @Override
  public List<Singer> findAllWithAlbums() {
    String sql = "select s.id,s.first_name,s.last_name,s.birth_date,a.id as album_id,a.singer_id,a.title,a.release_date" +
            " from singer s left join album a on s.id = a.singer_id";
    return namedParameterJdbcTemplate.query(sql,resultSetExtractor);
  }

  @Override
  public String findNameById(Long id) {
    String sql = "select first_name || ' ' || last_name from singer where id = :singerId";
    final HashMap<String, Object> map = new HashMap<>();
    map.put("singerId", id);
    return namedParameterJdbcTemplate.queryForObject(sql, map, String.class);
  }

  @Override
  public List<Singer> findAll() {
    String sql = "SELECT id,first_name,last_name,birth_date from singer";
    return namedParameterJdbcTemplate.query(sql, rowMapper);
  }

  @Override
  public List<Singer> findByFirstName(String firstName) {
    exception("findByFirstName");
    return null;
  }

  @Override
  public String findLastNameById(Long id) {
    exception("findLastNameById");
    return null;
  }

  @Override
  public String findFirstNameById(Long id) {
    exception("findFirstNameById");
    return null;
  }

  @Override
  public void insert(Singer singer) {
    exception("insert");
  }

  @Override
  public void update(Singer singer) {
    exception("update");
  }

  @Override
  public void delete(Long singerId) {
    exception("delete");
  }

  @Override
  public List<Singer> findAllWithDetail() {
    exception("findAllWithDetail");
    return null;
  }

  @Override
  public void insertWithDetail(Singer singer) {
    exception("insertWithDetail");
  }

  private void exception(String methodName) {
    throw new NotImplementedException(methodName);
  }
}
