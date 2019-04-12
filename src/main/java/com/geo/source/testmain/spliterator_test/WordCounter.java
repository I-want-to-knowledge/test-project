package com.geo.source.testmain.spliterator_test;

/**
 * 单词计数器
 *
 * @author YanZhen
 * @since 2019-04-12 10:34
 */
class WordCounter {

  private final int counter;
  private final boolean lastSpace;

  public WordCounter(int counter, boolean lastSpace) {
    this.counter = counter;
    this.lastSpace = lastSpace;
  }

  /**
   * 累加器
   * @param c 字符串
   * @return 累加结果
   */
  WordCounter accumulate(Character c) {
    if (Character.isWhitespace(c)) {
      return lastSpace ? this : new WordCounter(counter, true);
    } else {
      return lastSpace ? new WordCounter(counter + 1, false) : this;
    }
  }

  /**
   * 合并
   * @param wordCounter 另一线程累加结果
   * @return 合并累加结果
   */
  WordCounter combine(WordCounter wordCounter) {
    return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
  }

  public int getCounter() {
    return counter;
  }
}
