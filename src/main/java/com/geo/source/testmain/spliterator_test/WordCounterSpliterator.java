package com.geo.source.testmain.spliterator_test;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 拆分迭代器
 *
 * @author YanZhen
 * @since 2019-04-12 13:22
 */
public class WordCounterSpliterator implements Spliterator<Character> {

  private final String string;// 字符串
  private int currentChar = 0;// 拆分的位置

  WordCounterSpliterator(String string) {
    this.string = string;
    System.out.println(Thread.currentThread().getName());
  }

  @Override
  public boolean tryAdvance(Consumer<? super Character> action) {
    // 查看是否还有要处理的字符
    action.accept(string.charAt(currentChar++));
    return currentChar < string.length();
  }

  @Override
  public Spliterator<Character> trySplit() {
    int currentSize = string.length() - currentChar;
    if (currentSize < 10) {// 这里设置10个项目为一组，实战中要考虑这个数字很小
      return null;
    }
    for (int i = currentSize/2 + currentChar; i < string.length(); i++) {
      if (Character.isWhitespace(string.charAt(i))) {
        final Spliterator<Character> spliterator = new WordCounterSpliterator(string.substring(currentChar, i));
        currentChar = i;
        return spliterator;
      }
    }
    return null;
  }

  @Override
  public long estimateSize() {
    return string.length() - currentChar;
  }

  @Override
  public int characteristics() {
    return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
  }
}
