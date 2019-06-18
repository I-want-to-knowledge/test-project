package com.geo.source.spring_simple.example7_chapter.entity;

import com.geo.source.spring_simple.example7_chapter.AbstractEntity;

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
        @NamedQuery(name = "Singer.findById",
                query = "select distinct s from Singer s" +
                        " left join fetch s.albums a left join fetch s.instruments i " +
                        "where s.id = :id"),
        @NamedQuery(name = "Singer.findAllWithAlbum",
                query = "select distinct s from Singer s" +
                        " left join fetch s.albums a left join fetch s.instruments i")})
public class Singer extends AbstractEntity {
  private String firstName;
  private String lastName;
  private LocalDate birthDate;
  private Set<Album> albums = new HashSet<>();// 专辑

  private Set<Instrument> instruments = new HashSet<>();

  @Column(name = "first_name")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Column(name = "last_name")
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  // @Temporal(TemporalType.DATE)

  @Column(name = "birth_date")
  public LocalDate getBirthDate() {
    return birthDate;
  }
  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL, orphanRemoval = true)
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

  @ManyToMany
  @JoinTable(name = "singer_instrument",
          joinColumns = @JoinColumn(name = "singer_id"),
          inverseJoinColumns = @JoinColumn(name = "instrument_id"))
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
