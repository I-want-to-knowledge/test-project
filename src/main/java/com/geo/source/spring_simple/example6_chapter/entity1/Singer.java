package com.geo.source.spring_simple.example6_chapter.entity1;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * 歌手
 *
 * @author YanZhen
 * @since 2019-06-11 11:40
 */
public class Singer implements Serializable {
  private Long id;
  private String firstName;// 姓
  private String lastName;// 名
  private LocalDate birthDate;// 出生日期
  private List<Album> albums;// 专辑

  public void setId(Long id) {
    this.id = id;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public Long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setAlbums(List<Album> albums) {
    this.albums = albums;
  }

  public List<Album> getAlbums() {
    return albums;
  }

  public boolean addAlbum(Album album) {
    if (albums == null) {
      albums = new ArrayList<>();
    } else {
      if (albums.contains(album)) {
        return false;
      }
    }
    albums.add(album);
    return true;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Singer.class.getSimpleName() + "[", "]")
            .add("id=" + id)
            .add("firstName='" + firstName + "'")
            .add("lastName='" + lastName + "'")
            .add("birthDate=" + birthDate)
            .add("albums=" + albums)
            .toString();
  }
}
