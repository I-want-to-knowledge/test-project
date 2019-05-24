package com.geo.source.spring_simple.example4_chapter.propertyEditor;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 属性编辑器，测试
 *
 * @author YanZhen
 * @since 2019-05-24 11:41
 */
public class PropertyEditorBean {

  private byte[] bytes;
  private Character character;
  private Class cls;
  private boolean bool;
  private List<String> list;
  private LocalDateTime date;
  private Float floatV;
  private File file;
  private InputStream inputStream;
  private Locale locale;
  private Pattern pattern;
  private Properties properties;
  private String trimString;
  private URL url;

  public static void main(String[] args) {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:spring2/app-context-property-editor.xml");
    context.refresh();
    context.getBean(PropertyEditorBean.class);
    context.close();
  }

  public void setBytes(byte[] bytes) {
    System.out.println("Setter Bytes : " + Collections.singletonList(bytes));
    this.bytes = bytes;
  }

  public void setCharacter(Character character) {
    System.out.println("Setter Character : " + character);
    this.character = character;
  }

  public void setCls(Class cls) {
    System.out.println("Setter Class : " + cls.getName());
    this.cls = cls;
  }

  public void setBool(boolean bool) {
    System.out.println("Setter Boolean : " + bool);
    this.bool = bool;
  }

  public void setList(List<String> list) {
    System.out.println("Setter List : " + list);
    this.list = list;
  }

  public void setDate(LocalDateTime date) {
    System.out.println("Setter LocalDate : " + date);
    this.date = date;
  }

  public void setFloatV(Float floatV) {
    System.out.println("Setter Float : " + floatV);
    this.floatV = floatV;
  }

  public void setFile(File file) {
    System.out.println("Setter File : " + file.getName());
    this.file = file;
  }

  public void setInputStream(InputStream inputStream) {
    System.out.println("Setter InputStream : " + inputStream);
    this.inputStream = inputStream;
  }

  public void setLocale(Locale locale) {
    System.out.println("Setter Locale : " + locale);
    this.locale = locale;
  }

  public void setPattern(Pattern pattern) {
    System.out.println("Setter Pattern : " + pattern);
    this.pattern = pattern;
  }

  public void setProperties(Properties properties) {
    System.out.println("Setter Properties : " + properties);
    this.properties = properties;
  }

  public void setTrimString(String trimString) {
    System.out.println("Setter String : " + trimString);
    this.trimString = trimString;
  }

  public void setUrl(URL url) {
    System.out.println("Setter URL : " + url.toExternalForm());
    this.url = url;
  }

  public static class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {

    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
      registry.registerCustomEditor(LocalDateTime.class,
              new CustomLocalDateTimeEditor("yyyy-MM-dd HH:mm:ss"));
      registry.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
  }
}
