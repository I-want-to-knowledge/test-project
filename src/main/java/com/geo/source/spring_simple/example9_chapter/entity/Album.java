package com.geo.source.spring_simple.example9_chapter.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.StringJoiner;

/**
 * Spring 事务测试
 *
 * @author YanZhen
 * @since 2019-07-01 17:17
 */
@Entity
@Table(name = "album")
public class Album implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Long id;
  @Column
  private String title;
  @Column(name = "release_date")
  private LocalDate releaseDate;
  @Version
  @Column
  private int version;

  @ManyToOne
  @JoinColumn(name = "singer_id")
  private Singer singer;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public LocalDate getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(LocalDate releaseDate) {
    this.releaseDate = releaseDate;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public Singer getSinger() {
    return singer;
  }

  public void setSinger(Singer singer) {
    this.singer = singer;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Album.class.getSimpleName() + "[", "]")
            .add("id=" + id)
            .add("title='" + title + "'")
            .add("releaseDate=" + releaseDate)
            .add("version=" + version)
            .add("singer=" + singer)
            .toString();
  }
}
