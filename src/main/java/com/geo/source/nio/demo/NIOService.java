package com.geo.source.nio.demo;

import java.io.IOException;

/**
 * NIO服务
 *
 * @author YanZhen
 * @since 2019-03-30 18:08
 */
public interface NIOService {

  /**
   * 时钟服务器
   * NIO，非阻塞IO，重点是非阻塞而不是异步！
   */
  void m1() throws IOException;
}
