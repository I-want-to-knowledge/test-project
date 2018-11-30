package com.geo.source.testmain.abstractclass;

public abstract interface AbstractTest {
	
	default void method() {
		method1();
		method2();
		System.out.println("完成！");
	}

	abstract void method1();
	
	default void method2() {
		System.out.println("这是一个抽象接口的方法，第二步");
	}
}
