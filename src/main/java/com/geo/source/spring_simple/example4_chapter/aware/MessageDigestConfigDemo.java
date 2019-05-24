package com.geo.source.spring_simple.example4_chapter.aware;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 消息摘要配置
 *
 * @author YanZhen
 * @since 2019-05-24 09:35
 */
public class MessageDigestConfigDemo {

  public static void main(String[] args) {
    //m1();
    m2();
  }

  /**
   * 使用factory-bean和factory-method属性，添加依赖项
   */
  private static void m2() {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:spring2/app-context-aware.xml");
    context.refresh();
    final MessageDigestTest messageDigestTest2 = context.getBean("messageDigestTest2", MessageDigestTest.class);
    messageDigestTest2.digest("hello world!");
    context.close();
  }

  /**
   * 使用factory-bean和factory-method属性，添加依赖项
   */
  static class MessageDigestFactory {
    private String algorithmName = "MD5";
    public MessageDigest createInstance() throws NoSuchAlgorithmException {
      return MessageDigest.getInstance(algorithmName);
    }

    public void setAlgorithmName(String algorithmName) {
      this.algorithmName = algorithmName;
    }
  }

  /**
   * 使用@Bean注入的形式，利用FactoryBean接口
   */
  private static void m1() {
    final GenericApplicationContext context = new AnnotationConfigApplicationContext(MessageDigestConfig.class);
    final MessageDigestTest mDTest = context.getBean("mDTest", MessageDigestTest.class);
    mDTest.digest("hello world!");
    context.close();
  }

  @Configuration
  static class MessageDigestConfig {
    @Bean
    MessageDigestFactoryBean mDFactoryBean1() {
      final MessageDigestFactoryBean messageDigestFactoryBean = new MessageDigestFactoryBean();
      messageDigestFactoryBean.setAlgorithmName("SHA1");
      return messageDigestFactoryBean;
    }

    @Bean
    MessageDigestFactoryBean mDFactoryBean2() {
      return new MessageDigestFactoryBean();
    }

    @Bean
    MessageDigestTest mDTest() throws Exception {
      final MessageDigestTest messageDigestTest = new MessageDigestTest();
      messageDigestTest.setMessageDigest1(mDFactoryBean1().getObject());
      messageDigestTest.setMessageDigest2(mDFactoryBean2().getObject());
      return messageDigestTest;
    }
  }
}
