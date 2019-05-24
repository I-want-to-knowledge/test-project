package com.geo.source.spring_simple.example4_chapter.destroyMethod;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.GenericApplicationContext;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/**
 * 销毁Bean前的回调方法实现
 *
 * @author YanZhen
 * @since 2019-05-23 10:33
 */
public class DestructiveBeanTest {

  public static void main(String[] args) {
    final GenericApplicationContext context = new AnnotationConfigApplicationContext(DestructiveBeanConfig.class);
    context.getBean(BeanInject.class);
    System.out.println("Calling destroy()");
    context.close();
  }

  @Configuration
  static class DestructiveBeanConfig {
    @Lazy
    @Bean(destroyMethod = "destroy")
      // @Bean(initMethod = "init", destroyMethod = "destroy") // initMethod属性和@PostConstruct注解二选一
    BeanInject destructiveBeanTest() {
      final BeanInject inject = new BeanInject();
      inject.setFilePath("d:/test.txt");
      return inject;
    }
  }

  static class BeanInject {
    private File file;
    private String filePath;

    void setFilePath(String filePath) {
      this.filePath = filePath;
    }

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
        throw new IllegalArgumentException("You must specify the filePath property of " + BeanInject.class);
      }
      this.file = new File(filePath);
      this.file.createNewFile();
      System.out.println("File exists: " + file.exists());
    }
  }
}
