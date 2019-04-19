package com.geo.source.testmain.chain_responsibility;

/**
 * 责任链 处理对象
 *
 * @author YanZhen
 * @since 2019-04-13 13:49
 */
public abstract class ProcessingObject<T> {

  protected ProcessingObject<T> processingObject;

  public void setSuccessor(ProcessingObject<T> processingObject) {
    this.processingObject = processingObject;
  }

  T handle(T input) {
    T t = handleWork(input);
    if (processingObject != null) {
      return processingObject.handle(t);
    }
    return t;
  }

  protected abstract T handleWork(T input);

  static class P1 extends ProcessingObject<String> {

    @Override
    protected String handleWork(String input) {
      return "From Raoul, Mario and Alan: " + input;
    }
  }

  static class P2 extends ProcessingObject<String> {

    @Override
    protected String handleWork(String input) {
      return input.replaceAll("labda", "lambda");
    }
  }

}
