package com.geo.source.spring_simple.example7_chapter.entity;

import com.geo.source.spring_simple.example7_chapter.AbstractEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.StringJoiner;

/**
 * hibernate 框架，简单映射
 *
 * @author YanZhen
 * @since 2019-06-17 09:42
 */
@Entity
@Table(name = "album")
public class Album extends AbstractEntity {
  private String title;
  private LocalDate releaseDate;

  private Singer singer;

  public Album() {
  }

  public Album(Long id, String title, LocalDate releaseDate) {
    super.setId(id);
    this.title = title;
    this.releaseDate = releaseDate;
  }

  @Column
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Column(name = "release_date")
  public LocalDate getReleaseDate() {
    return releaseDate;
  }
  public void setReleaseDate(LocalDate releaseDate) {
    this.releaseDate = releaseDate;
  }

  @ManyToOne
  @JoinColumn(name = "singer_id")
  public Singer getSinger() {
    return singer;
  }

  public void setSinger(Singer singer) {
    this.singer = singer;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Album.class.getSimpleName() + "[", "]")
            .add("id=" + super.getId())
            .add("title='" + title + "'")
            .add("releaseDate=" + releaseDate)
            .toString();
  }
}
