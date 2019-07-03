package com.geo.source.spring_simple.example9_chapter.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

/**
 * 事务测试
 *
 * @author YanZhen
 * @since 2019-07-01 16:01
 */
@Entity
@Table(name = "singer")
@NamedQueries({@NamedQuery(name = Singer.FIND_ALL, query = "select s from Singer s"),
        @NamedQuery(name = Singer.COUNT_ALL, query = "select count(s) from Singer s")})
public class Singer implements Serializable {
  static final String FIND_ALL = "Singer.findAll";
  static final String COUNT_ALL = "Singer.countAll";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Long id;
  @Version
  @Column
  private int version;
  @Column(name = "first_name")
  private String firstName;
  @Column(name = "last_name")
  private String lastName;
  @Column(name = "birth_date")
  private LocalDate birthDate;

  @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Album> albums = new HashSet<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public Set<Album> getAlbums() {
    return albums;
  }

  public void setAlbums(Set<Album> albums) {
    this.albums = albums;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Singer.class.getSimpleName() + "[", "]")
            .add("id=" + id)
            .add("version=" + version)
            .add("firstName='" + firstName + "'")
            .add("lastName='" + lastName + "'")
            .add("birthDate=" + birthDate)
            // .add("albums=" + albums)
            .toString();
  }
}
