package com.geo.source.nio.demo.impl;

import com.geo.source.nio.demo.NIOService;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.SimpleFormatter;

/**
 * NIO
 *
 * @author YanZhen
 * @since 2019-03-30 18:11
 */
public class NIOServiceImpl implements NIOService {

  @Override
  public void m1() throws IOException {
    char name = 'A';
    // 创建通道，设置非阻塞
    ServerSocketChannel socketChannel = ServerSocketChannel.open();
    socketChannel.configureBlocking(false);
    // 创建选择器，并为通道绑定感兴趣的实践
    Selector selector = Selector.open();
    socketChannel.register(selector, SelectionKey.OP_ACCEPT).attach("主监听通道");
    System.out.println("监听的Channel id：" + socketChannel.hashCode());
    // 通道绑定端口号
    InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 4700);
    socketChannel.socket().bind(inetSocketAddress);

    while (true) {
      // 选择器选择通道或初始化一个通道
      int selectNow = selector.selectNow();
      if (selectNow > 0) {
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        while (iterator.hasNext()) {
          SelectionKey selectionKey = iterator.next();
          iterator.remove();
          // 判断通道是否准备好
          if (selectionKey.isAcceptable()) {
            ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
            System.out.println("可接受链接的Channel id:" + channel.hashCode() + selectionKey.attachment());
            SocketChannel channel1 = (SocketChannel) channel.accept().configureBlocking(false);
            System.out.println("接受链接后返回的Channel id:" + channel1.hashCode());
            channel1.register(selector, (SelectionKey.OP_READ | SelectionKey.OP_WRITE)).attach(name);
            name++;
          }

          // 判断通道是否可以写入
          if (selectionKey.isWritable()) {
            SocketChannel redChannel = (SocketChannel) selectionKey.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
            byteBuffer.put(("to" + selectionKey.attachment() + " "
                    + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\n").getBytes());
            byteBuffer.flip();
            redChannel.write(byteBuffer);
          }

          // 判断通道是否可以读取
          if (selectionKey.isReadable()) {
            SocketChannel channel = (SocketChannel) selectionKey.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
            channel.read(byteBuffer);
            System.out.println(selectionKey.attachment() + "" + getStr(byteBuffer));
          }
        }
      }

      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * ByteBuffer 转 String
   * @param byteBuffer 字节缓冲区
   * @return 字符串
   */
  private String getStr(ByteBuffer byteBuffer) {
    StringBuilder string = new StringBuilder();
    for (int i = 0; i < byteBuffer.position(); i++) {
      string.append(byteBuffer.get(i));
    }
    return string.toString();
  }
}
