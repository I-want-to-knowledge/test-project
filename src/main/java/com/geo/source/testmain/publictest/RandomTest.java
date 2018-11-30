package com.geo.source.testmain.publictest;

import java.util.Random;

public class RandomTest {

	public static void main(String[] args) {
		Random r = new Random();
		double d = r.nextInt(10000);
		System.out.println(d);
	}
}
