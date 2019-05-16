package com.geo.source.testmain.bean;

import java.lang.reflect.Method;

/**
 * 静态工厂注入
 *
 * @author YanZhen
 * @since 2019-03-25 13:22
 */
public class AbcFactory {
//静态工厂注入


  public static Abc getPersonInstance(String name, String id) throws Exception {


    Abc abc = (Abc) Class.forName("com.geo.source.testmain.bean.Abc").newInstance();


    Method m = abc.getClass().getMethod("setName", java.lang.String.class);


    m.invoke(abc, name);


    m = abc.getClass().getMethod("setId", String.class);


    m.invoke(abc, id);


    return abc;


  }
}
