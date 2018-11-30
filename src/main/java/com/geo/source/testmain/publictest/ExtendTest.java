package com.geo.source.testmain.publictest;

public class ExtendTest {

	public void test() {
		System.out.println("prent method!");
	}
}

class ExtendTest2 extends ExtendTest {
	
	@Override
	public void test() {
		System.out.println("submethod!");
	}
}