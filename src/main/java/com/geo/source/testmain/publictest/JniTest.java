package com.geo.source.testmain.publictest;

public class JniTest {
	public native void hello(String name);

  static{
      System.loadLibrary("hello");
  }

  public static void main(String[] args){
      new JniTest().hello("jni");
  }
}
