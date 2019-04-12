package com.geo.source.testmain.publictest;

/**
 * 工具类
 *
 * @author YanZhen
 * @since 2019-04-11 13:28
 */
public class XxxUtils {

  private static long time = 0L;

  /**
   * 开始时间
   */
  public static void start() {
    time = System.nanoTime();
  }

  /**
   * 时间差
   */
  public static long end() {
    final long l = (System.nanoTime() - time) / 1000_000;
    if (time == 0) {
      System.out.println("(" + l + ")无效时间差！！");
    }
    System.out.println(l);
    return l;
  }
}
