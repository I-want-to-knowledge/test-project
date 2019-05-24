package com.geo.source.spring_simple.example4_chapter.destroyMethod;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.File;

/**
 * 销毁Bean回调测试
 * xml
 *
 * @author YanZhen
 * @since 2019-05-23 08:56
 */
public class DestructiveBean implements InitializingBean {
  private File file;
  private String filePath;

  public static void main(String[] args) {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:spring2/app-context-destroy.xml");
    context.refresh();
    final DestructiveBean destructiveBean = context.getBean("destructiveBean", DestructiveBean.class);
    System.out.println("Calling destroy()");
    // context.destroy();
    context.registerShutdownHook();// 自动提示spring注册底层jvm运行时的关闭钩子，和close方法效果；
    // context.close();// 调用销毁方法
    System.out.println("Called destroy()");
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("Initializing Bean");
    if (filePath == null) {
      throw new IllegalArgumentException("You must specify the filePath property of " + DestructiveBean.class);
    }

    this.file = new File(filePath);
    this.file.createNewFile();

    System.out.println("File exists: " + file.exists());
  }

  /**
   * 销毁bean前回调方法
   * spring利用反射调用
   */
  private void destroy() {
    System.out.println("Destroying Bean");
    if (!file.delete()) {
      System.err.println("ERROR: failed to delete file.");
    }
    System.out.println("File exists: " + file.exists());
  }
}
