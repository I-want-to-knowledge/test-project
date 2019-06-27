package com.geo.source.spring_simple.example8_chapter.entity;

import com.geo.source.spring_simple.example8_chapter.AuditableEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.StringJoiner;

/**
 * Auditable 接口测试
 *
 * @author YanZhen
 * @since 2019-06-22 12:36
 */
@Entity
@Table(name = "singer_audit")
public class SingerAudit extends AuditableEntity<SingerAudit> {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Long id;
  @Column(name = "first_name")
  private String firstName;
  @Column(name = "last_name")
  private String lastName;
  @Column(name = "birth_date")
  private LocalDate birthDate;
  @Version
  @Column
  private int version;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", SingerAudit.class.getSimpleName() + "[", "]")
            .add("id=" + id)
            .add("firstName='" + firstName + "'")
            .add("lastName='" + lastName + "'")
            .add("birthDate=" + birthDate)
            .add("version=" + version)
            .add("createdBy='" + super.getCreatedBy() + "'")
            .add("createdDate=" + super.getCreatedDate())
            .add("lastModifiedBy='" + super.getLastModifiedBy() + "'")
            .add("lastModifiedDate=" + super.getLastModifiedDate())
            .toString();
  }
}
