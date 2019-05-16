package com.geo.source.spring_simple.example2.BeanFactoryImpl;

/**
 * @author YanZhen
 * @since 2019-05-15 15:05
 */
public class BookwormOracle implements Oracle {
  private String encyclopedia;

  public void setEncyclopedia(String encyclopedia) {
    this.encyclopedia = encyclopedia;
  }

  @Override
  public String defineMeaningOfLife() {
    return "Encyclopedias are a waste of money - go see the world instead";
  }
}
