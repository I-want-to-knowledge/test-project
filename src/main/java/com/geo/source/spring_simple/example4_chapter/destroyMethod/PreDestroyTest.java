package com.geo.source.spring_simple.example4_chapter.destroyMethod;

import org.springframework.context.support.GenericXmlApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;

/**
 * 销毁Bean前的回调方法
 * 使用PreDestroy注解，xml中需要添加 <context:annotation-config/> 标记
 * 使用该注解需要支持JSR-250
 * @author YanZhen
 * @since 2019-05-23 10:11
 */
public class PreDestroyTest {
  private File file;
  private String filePath;

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public static void main(String[] args) {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:spring2/app-context-destroy.xml");
    context.refresh();
    context.getBean("preDestroyTest", PreDestroyTest.class);
    System.out.println("Calling destroy()");
    context.close();
  }

  @PreDestroy
  private void destroy() {
    System.out.println("Destroying Bean");
    if (!file.delete()) {
      System.err.println("ERROR: failed to delete file.");
    }
    System.out.println("File exists: " + file.exists());
  }

  @PostConstruct
  private void init() throws IOException {
    System.out.println("Initializing Bean");

    if (filePath == null) {
      throw new IllegalArgumentException("You must specify the filePath property of " + PreDestroyTest.class);
    }
    this.file = new File(filePath);
    this.file.createNewFile();
    System.out.println("File exists: " + file.exists());
  }
}
