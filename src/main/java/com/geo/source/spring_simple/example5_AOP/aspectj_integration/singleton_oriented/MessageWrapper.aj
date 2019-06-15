package com.geo.source.spring_simple.example5_AOP.aspectj_integration.singleton_oriented;

/**
 * 单例切面，测试
 * @author YanZhen
 * @since 2019-06-10 10:14
 */
public aspect MessageWrapper {
  private String prefix;
  private String suffix;

  public String getPrefix() {
    return prefix;
  }

  public String getSuffix() {
    return suffix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public void setSuffix(String suffix) {
    this.suffix = suffix;
  }

  pointcut doWriting(): execution(* MessageWriter.writerMessage());

  before(): doWriting() {
    System.out.println(prefix);
  }

  after(): doWriting() {
    System.out.println(suffix);
  }
}
