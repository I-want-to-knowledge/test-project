package com.geo.source.testmain.publictest;

import com.geo.source.testmain.bean.Abc;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.*;

/**
 * 对象
 * 基本数据类型和普通对象
 *
 * @author YanZhen
 * @since 2019-04-01 13:30
 */
public class ObjectTest {
  public static void main(String[] args) {
    // m();
    //m4();
    // m5();
//    m6();
//    m7();
    m8();
  }

  /**
   * java反射机制
   * NoSuchFieldException异常原因：①没有对应字段；②属性为私有时获取Field用的方法不是getDeclaredField
   */
  private static void m8() {
    /*final Class<Aa> aaClass = Aa.class;// 不会初始化
    try {
      final Aa aa = aaClass.newInstance();
      final Field id = aaClass.getDeclaredField(aaClass.getDeclaredFields()[0].getName());
      id.setAccessible(true);// 启用禁用访问安全检查开关，true禁用
      id.set(aa, "1");
      final Field name = aaClass.getDeclaredField(aaClass.getDeclaredFields()[1].getName());
      name.setAccessible(true);// 启用禁用访问安全检查开关，true禁用
      name.set(aa, "2");
      out.println(aa);
    } catch (InstantiationException | IllegalAccessException | NoSuchFieldException e) {
      e.printStackTrace();
    }*/

    try {
      final Class<?> abcClass = Class.forName("com.geo.source.testmain.bean.Abc");// 会初始化
//      abcClass.getConstructors();//获取公用构造方法
//      abcClass.getDeclaredConstructors();// 获取所有构造方法
//      abcClass.getConstructor(null);// 获取公有无惨构造方法
      final Object instance = abcClass.newInstance();
      final Field id = abcClass.getDeclaredField(abcClass.getDeclaredFields()[0].getName());
      id.setAccessible(true);// 启用禁用访问安全检查开关，true禁用
      id.set(instance, "1");
      final Field name = abcClass.getDeclaredField(abcClass.getDeclaredFields()[1].getName());
      name.setAccessible(true);// 启用禁用访问安全检查开关，true禁用
      name.set(instance, "2");
      out.println((Abc) instance);
    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchFieldException e) {
      e.printStackTrace();
    }
  }

  private static void m7() {
    /*Abc a = new Abc();
    a.setName("1");
    a.setId("1.1");
    Abc b = a;// 指针引用
    b.setId("2");
    b.setName("2.1");
    out.println("a="+a);
    out.println("b="+b);*/
  }

  private static void m6() {
    /*final Abc abc = new Abc();
    abc.setId("1");
    abc.setName("abc");
    out.println(abc);*/
  }

  private static void m5() {
    int a = 1;
    Integer b = null;
    out.println(a == b);
  }

  private static void m4() {
    final int a = 1;
    Runnable o = () -> out.println(a);
    // a = 2;
  }

  private static void m() {
    Integer integer = new Integer(10);
    m1(integer);
    out.println("2修改后=" + integer);

    String a = "";
    m2(a);
    out.println("2:" + a);

    Map<String, String> map = new HashMap<>();
    map.put("1a", "1a");
    m3(map);
    out.println("map2=" + map);
  }

  private static void m3(Map<String, String> map) {
    map.clear();
    map.put("2a", "2a");
    out.println("map1=" + map);
  }

  private static void m2(String a) {
    a = "123a";
    out.println("1:" + a);
  }

  private static void m1(Integer integer) {
    integer = 100;
    out.println("1修改后=" + integer);
  }
}
