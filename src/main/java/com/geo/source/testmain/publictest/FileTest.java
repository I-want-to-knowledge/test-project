package com.geo.source.testmain.publictest;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    // m2();
    // m3();
    // m4();
    m5();
  }

  private static void m5() {
    final ArrayList<Integer> objects = new ArrayList<>();
    objects.add(1);
    objects.add(2);
    objects.add(3);
    objects.add(4);
    final Optional<Integer> any = objects.stream().filter(integer -> integer == 0).findAny();
    if (any.isPresent()){
      System.out.println(true);
      any.ifPresent(System.out::println);
    }
    System.out.println(any.orElse(0));
  }

  private static void m4() {
    Stream.iterate(new int[]{0, 1}, n -> new int[] {n[1], n[0] + n[1]}).limit(20).forEach(ints -> System.out.println(ints[0] + ":" + ints[1]));
  }

  private static void m3() {
    long a = 0;
    try (Stream<String> lines = Files.lines(Paths.get("D:\\test\\a.txt"), Charset.forName("GBK"))) {
      lines.flatMap(s -> Arrays.stream(s.split(" "))).distinct().forEach(System.out::println);
    } catch (IOException e) {
      e.printStackTrace();
    }
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
