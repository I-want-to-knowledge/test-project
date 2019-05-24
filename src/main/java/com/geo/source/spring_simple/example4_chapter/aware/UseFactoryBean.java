package com.geo.source.spring_simple.example4_chapter.aware;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * 使用FactoryBean接口，不适用new运算符创建依赖
 *
 * @author YanZhen
 * @since 2019-05-23 18:17
 */
public class UseFactoryBean {

  public static void main(String[] args) {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:spring2/app-context-aware.xml");
    context.refresh();
    final MessageDigestTest messageDigestTest = context.getBean("messageDigestTest", MessageDigestTest.class);
    messageDigestTest.digest("hello world!");
    context.close();
  }
}

class MessageDigestFactoryBean implements FactoryBean<MessageDigest>, InitializingBean {
  private String algorithmName = "MD5";

  private MessageDigest messageDigest = null;

  @Override
  public MessageDigest getObject() {
    return messageDigest;
  }

  @Override
  public Class<MessageDigest> getObjectType() {
    return MessageDigest.class;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    messageDigest = MessageDigest.getInstance(algorithmName);
  }

  @Override
  public boolean isSingleton() {
    return true;
  }

  public void setAlgorithmName(String algorithmName) {
    this.algorithmName = algorithmName;
  }

  public String createInstance() {
    return null;
  }
}

class MessageDigestTest {
  private MessageDigest messageDigest1;
  private MessageDigest messageDigest2;

  public void setMessageDigest1(MessageDigest messageDigest1) {
    this.messageDigest1 = messageDigest1;
  }

  public void setMessageDigest2(MessageDigest messageDigest2) {
    this.messageDigest2 = messageDigest2;
  }

  void digest(String msg) {
    System.out.println("Using message digest1");
    digest(msg, messageDigest1);

    System.out.println("Using message digest2");
    digest(msg, messageDigest2);
  }

  private void digest(String msg, MessageDigest messageDigest) {
    System.out.println("Using algorithm: " + messageDigest.getAlgorithm());
    messageDigest.reset();
    final byte[] bytes = msg.getBytes();
    final byte[] out = messageDigest.digest(bytes);
    System.out.println(Arrays.toString(out));
    System.out.println("MessageDigest Object : "+messageDigest.toString());
  }
}
