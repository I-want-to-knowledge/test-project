package com.geo.source.testmain.chain_responsibility;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * 责任链模式
 *
 * @author YanZhen
 * @since 2019-04-13 13:56
 */
public class ChainResponsibilityMain {
  public static void main(String[] args) {
    // m1();
    m2();
  }

  /**
   * 基本的责任链模式
   */
  private static void m1() {
    //
    final ProcessingObject<String> p1 = new ProcessingObject.P1();
    final ProcessingObject<String> p2 = new ProcessingObject.P2();
    p1.setSuccessor(p2);
    final String handle = p1.handle("labdas over!!!");
    System.out.println(handle);
  }

  /**
   * 用lambda表达式表示责任链模式
   */
  private static void m2() {
    UnaryOperator<String> p3 = s3 -> 111 + s3;
    UnaryOperator<String> p1 = s1 -> "From Raoul, Mario and Alan: " + s1;
    UnaryOperator<String> p2 = s2 -> s2.replaceAll("labda", "lambda");
    final Function<String, String> stringStringFunction = p3.andThen(p1).andThen(p2);
    final String apply = stringStringFunction.apply("labdas over!!!");
    System.out.println(apply);
  }

}
