package com.geo.source.spring_simple.example6_chapter.entity1;

import java.time.LocalDate;
import java.util.StringJoiner;

/**
 * 专辑
 *
 * @author YanZhen
 * @since 2019-06-11 11:43
 */
public class Album {
  private Long id;
  private Long singerId;// 歌手id
  private String title;// 标题
  private LocalDate releaseDate;// 发布时间

  public Album() {
  }

  public Album(String title, LocalDate releaseDate) {
    this.title = title;
    this.releaseDate = releaseDate;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setSingerId(Long singerId) {
    this.singerId = singerId;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setReleaseDate(LocalDate releaseDate) {
    this.releaseDate = releaseDate;
  }

  public Long getId() {
    return id;
  }

  public Long getSingerId() {
    return singerId;
  }

  public String getTitle() {
    return title;
  }

  public LocalDate getReleaseDate() {
    return releaseDate;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Album.class.getSimpleName() + "[", "]")
            .add("id=" + id)
            .add("singerId=" + singerId)
            .add("title='" + title + "'")
            .add("releaseDate=" + releaseDate)
            .toString();
  }
}
