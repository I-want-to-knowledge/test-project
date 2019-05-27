package com.geo.source.spring_simple.example4_chapter.resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

/**
 * spring 内置的资源访问，测试
 * 文件系统、类路径、远程服务器 都可以访问
 *
 * @author YanZhen
 * @since 2019-05-25 17:01
 */
public class ResourceDemo {

  public static void main(String[] args) throws IOException {
    final ApplicationContext context = new ClassPathXmlApplicationContext();
    final File file = File.createTempFile("test", "txt", new File("d:/"));
    file.deleteOnExit();// 程序终止时删除

    final Resource resource1 = context.getResource("file:" + file.getPath());
    displayInfo(resource1);
    final Resource resource2 = context.getResource("classpath:test.txt");
    displayInfo(resource2);
    final Resource resource3 = context.getResource("http://www.baidu.com");
    displayInfo(resource3);

    if (file.delete()) {
      System.out.println("删除成功！");
    }
  }

  private static void displayInfo(Resource resource) throws IOException {
    System.out.println(resource.getClass());
    System.out.println(resource.getURL().getContent());
    System.out.println();
  }
}
