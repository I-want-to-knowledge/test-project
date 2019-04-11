package com.geo.source.testmain.publictest;

import java.math.BigDecimal;
import java.util.stream.IntStream;

/**
 * math
 *
 * @author YanZhen
 * 2018-12-17 13:37:29
 * MathTest
 */
public class MathTest {

	public static void main(String[] args) {
		// m1();
//		m2();
//		m3();
		m4();
	}

	private static void m4() {
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
	}

	/**
	 * 求质数
	 */
	private static void m3() {
		int a = 11;
		final int sqrt = (int) Math.sqrt(a);
		final boolean b = IntStream.rangeClosed(2, sqrt).noneMatch(i -> a % i == 0);
		System.out.println(b);
	}

	private static void m2() {
		final BigDecimal bigDecimal = new BigDecimal("2.0");
		System.out.println(new BigDecimal("2.00").equals(bigDecimal));
		System.out.println(new BigDecimal("2.00").compareTo(bigDecimal));
	}

	private static void m1() {
		System.out.println(Math.random());
	}

}
