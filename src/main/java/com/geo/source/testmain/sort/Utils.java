package com.geo.source.testmain.sort;

/**
 * 工具类
 *
 * @author YanZhen
 * @since 2019-03-16 11:33
 */
class Utils {
  private Utils() {
  }

  /**
   * 获取数字的指定位的数
   *
   * @param a 数字
   * @param b 获取第几位(从个位开始为第一位)
   * @return 位上数字
   */
  static int digit(int a, int b) {
    char[] nums = String.valueOf(a).toCharArray();
    int i = nums.length;
    if (i < b)
      return 0;
    return Integer.valueOf(nums[i - b] + "");
  }
}
