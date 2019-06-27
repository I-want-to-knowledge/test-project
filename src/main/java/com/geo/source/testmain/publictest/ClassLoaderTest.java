package com.geo.source.testmain.publictest;

import java.io.InputStream;

/**
 * 类加载测试
 *
 * @author YanZhen
 * @since 2019-06-25 08:44
 */
public class ClassLoaderTest {
  /*static {
    if (true) {
      System.out.println(Thread.currentThread().getName() + " init start");
      while (true) {
      }
    }
  }*/

  public static void main(String[] args) {
    ClassLoader classLoader = new ClassLoader() {
      @Override
      public InputStream getResourceAsStream(String name) {
        final String fileName = name.substring(name.indexOf(".") + 1) + ".class";
        return super.getResourceAsStream(name);
      }
    };
  }
}
