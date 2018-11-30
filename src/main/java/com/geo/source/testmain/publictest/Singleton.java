package com.geo.source.testmain.publictest;

public class Singleton {
	private static Singleton uniqueInstance;
	private static int a = 0;

	private Singleton() {}

	public static Singleton getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Singleton();
		}
		return uniqueInstance;
	}
	
	public synchronized void test() {
		System.out.println("a的值是：" + (a++));
	}
}
