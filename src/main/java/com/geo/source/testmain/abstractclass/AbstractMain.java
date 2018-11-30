package com.geo.source.testmain.abstractclass;

public class AbstractMain {

	public static void main(String[] args) {
		AbstractTest a = new AbstractTestImpl1();
		a.method();
		AbstractTest ab = new AbstractTestImpl2();
		ab.method();
	}

}
