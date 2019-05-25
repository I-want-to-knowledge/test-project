package com.geo.source.spring_simple.example4_chapter.i18n;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Locale;

/**
 * 国际化测试
 *
 * @author YanZhen
 * @since 2019-05-25 12:17
 */
public class MessageSourceDemo {
  public static void main(String[] args) {
    // m1();
    m2();
  }

  /**
   * 独立的应用程序中使用MessageSource
   */
  private static void m2() {
    final GenericApplicationContext context = new AnnotationConfigApplicationContext(MessageSourceConfig.class);
    final Locale simplifiedChinese = Locale.SIMPLIFIED_CHINESE;
    final Locale traditionalChinese = Locale.TRADITIONAL_CHINESE;

    System.out.println(context.getMessage("name", new Object[]{"yz1", "yz2"}, simplifiedChinese));
    System.out.println(context.getMessage("name", new Object[]{"yz1", "yz2"}, traditionalChinese));

    context.close();
  }

  @Configuration
  static class MessageSourceConfig {
    @Bean
    ResourceBundleMessageSource messageSource() {
      final ResourceBundleMessageSource source = new ResourceBundleMessageSource();
      source.setBasenames("spring2/labels");
      return source;
    }

  }

  /**
   * 使用ApplicationContext作为MessageSource，提取国际化信息
   */
  private static void m1() {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:spring2/app-context-i18n.xml");
    context.refresh();

    final Locale english = Locale.ENGLISH;
    final Locale german = new Locale("de", "DE");

    System.out.println(context.getMessage("msg", null, english));
    System.out.println(context.getMessage("msg", null, german));
    System.out.println(context.getMessage("nameMsg", new Object[]{"yz1", "yz2"}, english));
    System.out.println(context.getMessage("nameMsg", new Object[]{"yz1", "yz2"}, german));

    context.close();
  }
}
