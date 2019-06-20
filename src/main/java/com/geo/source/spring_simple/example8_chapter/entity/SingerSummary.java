package com.geo.source.spring_simple.example8_chapter.entity;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * jpa2 测试
 *
 * @author YanZhen
 * @since 2019-06-19 10:03
 */
public class SingerSummary implements Serializable {
  private String firstName;
  private String lastName;
  private String latestAlbum;

  public SingerSummary(String firstName, String lastName, String latestAlbum) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.latestAlbum = latestAlbum;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getLatestAlbum() {
    return latestAlbum;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", SingerSummary.class.getSimpleName() + "[", "]")
            .add("firstName='" + firstName + "'")
            .add("lastName='" + lastName + "'")
            .add("latestAlbum='" + latestAlbum + "'")
            .toString();
  }
}
