package com.geo.source.nio;

import com.geo.source.nio.demo.NIOService;
import com.geo.source.nio.demo.impl.NIOServiceImpl;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * NIO
 *
 * @author YanZhen
 * @since 2019-03-30 16:33
 */
public class NioMain {
  public static void main(String[] args) {
    // m1();
    m2();
  }

  private static void m2() {
    NIOService nioService = new NIOServiceImpl();
    try {
      nioService.m1();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 创建一个path实例
   * path--io.File
   */
  private static void m1() {
    Path path = Paths.get("F:\\",".\\RentalBookingDao.xml");
    System.out.println(path);

    Path path1 = path.normalize();
    System.out.println(path1);
  }
}
