package com.geo.source.testmain.publictest;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * 文件
 *
 * @author YanZhen
 * 2018-11-22 15:04:20
 * FileTest
 */
public class FileTest {

  public static void main(String[] args) {
    // m1();
    m2();
  }

  private static void m2() {
    try {
      String s = m2_1(b -> b.readLine() + "\n" + b.readLine() + "\n" + b.readLine());
      System.out.println(s);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static String m2_1(ProcessFile processFile) throws Exception {
    String process = null;
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader("F:\\RentalBookingDao.xml"));) {
      process = processFile.process(bufferedReader);
    }/*
    System.out.println("=======================");
    System.out.println(bufferedReader.readLine());
    System.out.println(bufferedReader.readLine());
    System.out.println(bufferedReader.readLine());
    System.out.println(bufferedReader.readLine());
    System.out.println(bufferedReader.readLine());
    System.out.println("=======================");*/
    return process;
  }

  private static void m1() {
    File file = new File("src/main/resources/test.xml");
    System.out.println(file.getName());
    System.out.println(file.getParent());
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    try {
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(file);
      System.out.println(doc.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FunctionalInterface
  private interface ProcessFile {
    String process(BufferedReader bufferedReader) throws Exception;
  }

}
