package com.geo.source.testmain.publictest;

import java.util.*;
import java.util.stream.Collectors;

/**
 * collection
 *
 * @author YanZhen
 * @since 2019-03-22 12:13
 */
public class CollectionTest {

  public static void main(String[] args) {
    // m1();
    m2();
  }

  private static void m2() {
    List<A> objects = new ArrayList<A>();
    objects.add(new A(1,"2"));
    objects.add(new A(3,"4"));
    System.out.println(Arrays.toString(objects.stream().map(A::getX).toArray()));
    System.out.println(objects.stream().map(A::getX).collect(Collectors.toList()));
  }

  private static void m1() {
    Collection<A> objects = new ArrayList<A>();
    objects.add(new A(1,"2"));
    objects.add(new A(3,"4"));
    System.out.println(objects.stream().findFirst().map(A::getX).orElse(0));
  }

  private static class A {
    private Integer x;
    private String y;

    public A(Integer x, String y) {
      this.x = x;
      this.y = y;
    }

    public A() {}

    public Integer getX() {
      return x;
    }

    public String getY() {
      return y;
    }
  }
}
