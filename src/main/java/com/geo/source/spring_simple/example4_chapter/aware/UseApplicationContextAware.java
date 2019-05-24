package com.geo.source.spring_simple.example4_chapter.aware;

import com.geo.source.spring_simple.example4_chapter.destroyMethod.PreDestroyTest;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;

/**
 * 使用ApplicationContextAware接口查找bean或销毁bin
 *
 * @author YanZhen
 * @since 2019-05-23 17:21
 */
public class UseApplicationContextAware {

  public static void main(String[] args) {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:spring2/app-context-aware.xml");
    // context.registerShutdownHook();
    context.refresh();
    context.getBean("destructiveBeanWithInterface", DestructiveBeanWithInterface.class);
  }

  /**
   * 使用ApplicationContextAware接口销毁bin
   */
  static class ShutdownHookBean implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      if (applicationContext instanceof GenericApplicationContext) {
        System.out.println("为Bean产生关闭钩子！！");
        ((GenericApplicationContext) applicationContext).registerShutdownHook();
      }

      System.out.println(applicationContext.getBean("destructiveBeanWithInterface",
              DestructiveBeanWithInterface.class));
    }
  }

  /**
   * 要注入的Bean
   */
  static class DestructiveBeanWithInterface {
    private File file;
    private String filePath;

    public void setFilePath(String filePath) {
      this.filePath = filePath;
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
        throw new IllegalArgumentException("You must specify the filePath property of " + DestructiveBeanWithInterface.class);
      }
      this.file = new File(filePath);
      this.file.createNewFile();
      System.out.println("File exists: " + file.exists());
    }

    @Override
    public String toString() {
      return "文件路径为：" + this.filePath;
    }
  }
}
