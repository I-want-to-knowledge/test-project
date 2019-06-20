package com.geo.source.spring_simple.example8_chapter.entity;

import com.geo.source.spring_simple.example8_chapter.AbstractEntity8;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

/**
 * hibernate 框架，简单映射
 *
 * @author YanZhen
 * @since 2019-06-17 09:34
 */
@Entity
@Table(name = "singer")
@NamedQueries({
        @NamedQuery(name = Singer.FIND_ALL, query = "select s from Singer s"),
        @NamedQuery(name = Singer.FIND_BY_ID,
                query = "select distinct s from Singer s" +
                        " left join fetch s.albums a left join fetch s.instruments i " +
                        "where s.id = :id"),
        @NamedQuery(name = Singer.FIND_ALL_WITH_ALBUM,
                query = "select distinct s from Singer s" +
                        " left join fetch s.albums a left join fetch s.instruments i")})
// 该注解第8章使用
@SqlResultSetMapping(name = "singerResult", entities = @EntityResult(entityClass = Singer.class))
public class Singer extends AbstractEntity8 {
  public static final String FIND_ALL = "Singer.findAll";
  public static final String FIND_BY_ID = "Singer.findById";
  public static final String FIND_ALL_WITH_ALBUM = "Singer.findAllWithAlbum";

  @Column(name = "first_name")
  private String firstName;
  @Column(name = "last_name")
  private String lastName;
  @Column(name = "birth_date")
  private LocalDate birthDate;
  @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Album> albums = new HashSet<>();// 专辑

  @ManyToMany
  @JoinTable(name = "singer_instrument",
          joinColumns = @JoinColumn(name = "singer_id"),
          inverseJoinColumns = @JoinColumn(name = "instrument_id"))
  private Set<Instrument> instruments = new HashSet<>();

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

  public boolean addAlbum(Album album) {
    album.setSinger(this);
    return getAlbums().add(album);
  }

  public boolean removeAlbum(Album album) {
    return getAlbums().remove(album);
  }

  public Set<Instrument> getInstruments() {
    return instruments;
  }

  public void setInstruments(Set<Instrument> instruments) {
    this.instruments = instruments;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Singer.class.getSimpleName() + "[", "]")
            .add("id=" + super.getId())
            .add("firstName='" + firstName + "'")
            .add("lastName='" + lastName + "'")
            .add("birthDate=" + birthDate)
            .add("albums=" + albums)
            .add("instruments=" + instruments)
            .toString();
  }
}
