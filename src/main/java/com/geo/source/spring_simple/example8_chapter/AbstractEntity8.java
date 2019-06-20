package com.geo.source.spring_simple.example8_chapter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 公共抽象实体
 *
 * @author YanZhen
 * @since 2019-06-18 10:06
 */
@MappedSuperclass
public class AbstractEntity8 implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)// 如何生成id
  @Column(updatable = false)// 不可更新
  private Long id;
  @Version
  @Column
  private int version;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
