package com.geo.source.testmain.publictest;

import java.io.*;
import java.util.Arrays;

/**
 * IO操作测试
 *
 * @author YanZhen
 * @since 2019-05-25 11:16
 */
public class IOTest {
  public static void main(String[] args) throws IOException {
    String file = "d:/stream.txt";
    String charset = "UTF-8";
    // 写字符换转成字节流
    FileOutputStream outputStream = new FileOutputStream(file);

    try (OutputStreamWriter writer = new OutputStreamWriter(
            outputStream, charset);) {
      writer.write("这是要保存的中文字符");
    }
    // 读取字节转换成字符
    FileInputStream inputStream = new FileInputStream(file);

    StringBuilder buffer = new StringBuilder();
    char[] buf = new char[64];
    int count = 0;
    try (InputStreamReader reader = new InputStreamReader(
            inputStream, charset);) {
      while ((count = reader.read(buf)) != -1) {
        buffer.append(buf, 0, count);
      }

      System.out.println("字节长度：" + "这是要保存的中文字符".getBytes(charset).length);
      final char[] chars = "这是要保存的中文字符".toCharArray();
      for (char aChar : chars) {
        System.out.println("字符码：" + aChar + " = " + (int) aChar);
      }
    }

    System.out.println(buffer.toString());
  }
}
