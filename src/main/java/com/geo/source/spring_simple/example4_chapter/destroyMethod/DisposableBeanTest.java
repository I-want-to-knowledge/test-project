package com.geo.source.spring_simple.example4_chapter.destroyMethod;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.File;

/**
 * 以实现DisposableBean接口的方法作为销毁前回调方法
 *
 * @author YanZhen
 * @since 2019-05-23 09:43
 */
public class DisposableBeanTest implements InitializingBean, DisposableBean {
  private File file;
  private String filePath;

  public static void main(String[] args) {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:spring2/app-context-destroy.xml");
    context.refresh();
    context.getBean("disposableBeanTest", DisposableBeanTest.class);
    System.out.println("Calling destroy()");
    context.close();
  }

  @Override
  public void destroy() throws Exception {
    System.out.println("Destroying Bean");
    if (!file.delete()) {
      System.err.println("ERROR: failed to delete file.");
    }
    System.out.println("File exists: " + file.exists());
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("Initializing Bean");

    if (filePath == null) {
      throw new IllegalArgumentException("You must specify the filePath property of " + DisposableBeanTest.class);
    }

    this.file = new File(filePath);
    this.file.createNewFile();
    System.out.println("File exists: " + file.exists());
  }
}
