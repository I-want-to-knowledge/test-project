package com.geo.source.spring_simple.example7_chapter.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

/**
 * hibernate框架，
 *
 * @author YanZhen
 * @since 2019-06-17 11:16
 */
@Entity
@Table(name = "instrument")
public class Instrument implements Serializable {
  @Id
  @Column(name = "instrument_id")
  private String instrumentId;
  @ManyToMany
  @JoinTable(name = "singer_instrument",
          joinColumns = @JoinColumn(name = "instrument_id"),
          inverseJoinColumns = @JoinColumn(name = "singer_id"))
  private Set<Singer> singers = new HashSet<>();

  public Set<Singer> getSingers() {
    return singers;
  }

  public void setSingers(Set<Singer> singers) {
    this.singers = singers;
  }

  public String getInstrumentId() {
    return instrumentId;
  }

  public void setInstrumentId(String instrumentId) {
    this.instrumentId = instrumentId;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Instrument.class.getSimpleName() + "[", "]")
            .add("instrumentId='" + instrumentId + "'")
            .toString();
  }
}
