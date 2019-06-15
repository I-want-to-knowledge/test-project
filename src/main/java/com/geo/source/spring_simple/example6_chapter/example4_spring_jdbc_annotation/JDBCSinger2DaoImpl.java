package com.geo.source.spring_simple.example6_chapter.example4_spring_jdbc_annotation;

import com.geo.source.spring_simple.example6_chapter.entity1.Album;
import com.geo.source.spring_simple.example6_chapter.entity1.Singer;
import com.geo.source.spring_simple.example6_chapter.example1_jdbc.SingerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import sun.dc.pr.PRError;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.*;

/**
 * jdbc 注解 测试
 *
 * @author YanZhen
 * @since 2019-06-13 14:27
 */
@Repository("singerDao2")
public class JDBCSinger2DaoImpl implements SingerDao {

  private static final Logger logger = LoggerFactory.getLogger(JDBCSinger2DaoImpl.class);
  private DataSource dataSource;
  private SelectAllSingers selectAllSingers;
  private SelectSingerByFirstName selectSingerByFirstName;
  private UpdateSinger updateSinger;
  private InsertSinger insertSinger;
  private InsertSingerAlbum insertSingerAlbum;
  private StoredFunctionFirstNameById storedFunctionFirstNameById;

  @Resource
  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
    selectAllSingers = new SelectAllSingers(dataSource);
    selectSingerByFirstName = new SelectSingerByFirstName(dataSource);
    updateSinger = new UpdateSinger(dataSource);
    insertSinger = new InsertSinger(dataSource);
    insertSingerAlbum = new InsertSingerAlbum(dataSource);
    storedFunctionFirstNameById = new StoredFunctionFirstNameById(dataSource);
  }

  @Override
  public List<Singer> findAll() {
    return selectAllSingers.execute();
  }

  @Override
  public List<Singer> findByFirstName(String firstName) {
    final HashMap<String, Object> map = new HashMap<>();
    map.put("first_name", firstName);
    return selectSingerByFirstName.executeByNamedParam(map);
  }

  @Override
  public String findLastNameById(Long id) {
    return null;
  }

  @Override
  public String findFirstNameById(Long id) {
    final List<String> singers = storedFunctionFirstNameById.execute(id);
    return singers.get(0);
  }

  @Override
  public void insert(Singer singer) {
    Map<String, Object> param = new HashMap<>();
    param.put("first_name", singer.getFirstName());
    param.put("last_name", singer.getLastName());
    param.put("birth_date", singer.getBirthDate());
    final GeneratedKeyHolder holder = new GeneratedKeyHolder();
    insertSinger.updateByNamedParam(param, holder);
    singer.setId(Objects.requireNonNull(holder.getKey()).longValue());
  }

  @Override
  public void update(Singer singer) {
    Map<String, Object> param = new HashMap<>();
    param.put("first_name", singer.getFirstName());
    param.put("last_name", singer.getLastName());
    param.put("birth_date", singer.getBirthDate());
    param.put("id", singer.getId());
    updateSinger.updateByNamedParam(param);
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
  public void insertWithAlbums(Singer singer) {
    Map<String, Object> params = new HashMap<>();
    params.put("first_name", singer.getFirstName());
    params.put("last_name", singer.getLastName());
    params.put("birth_date", singer.getBirthDate());
    KeyHolder keyHolder = new GeneratedKeyHolder();
    insertSinger.updateByNamedParam(params, keyHolder);
    final long singerId = Objects.requireNonNull(keyHolder.getKey()).longValue();
    final List<Album> albums = singer.getAlbums();
    if (albums != null) {
      for (Album album : albums) {
        params = new HashMap<>();
        params.put("singer_id", singerId);
        params.put("title", album.getTitle());
        params.put("release_date", album.getReleaseDate());
        insertSingerAlbum.updateByNamedParam(params);
      }
    }
    insertSingerAlbum.flush();// 最后一批不足批处理大小的插入操作提交
  }

  @Override
  public List<Singer> findAllWithAlbums() {
    final String sql = "select s.id,s.first_name,s.last_name,s.birth_date," +
            "a.id as album_id,a.singer_id,a.title,a.release_date " +
            "from singer s left join album a on s.id = a.singer_id";
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    return namedParameterJdbcTemplate.query(sql, resultSetExtractor);
  }

  private final ResultSetExtractor<List<Singer>> resultSetExtractor = rs -> {
    Map<Long, Singer> params = new HashMap<>();
    Singer singer;
    while (rs.next()) {
      final long singerId = rs.getLong("id");
      singer = params.get(singerId);
      if (singer == null) {
        singer = new Singer();
        singer.setId(singerId);
        singer.setFirstName(rs.getString("first_name"));
        singer.setLastName(rs.getString("last_name"));
        singer.setBirthDate(rs.getDate("birth_date").toLocalDate());
        params.put(singerId, singer);
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
    return new ArrayList<>(params.values());
  };
}
