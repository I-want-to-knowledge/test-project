package com.geo.source.testmain.publictest;

import java.math.BigDecimal;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

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
//		m4();
//		m5();
		m6();
	}

	private static void m6() {
		long a = 1000;
		Number number = a;
	}

	private static void m5() {
		long n = 10_000_000;
		long fastest = Long.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			long a = 0;
			XxxUtils.start();
			final long reduce = LongStream.iterate(1, operand -> operand + 1).unordered().limit(n).reduce(0, Long::sum);
			// LongStream.rangeClosed(0, n).parallel().reduce(0, Long::sum);
			/*for (int j = 1; j <= n; j++) {
				a = a + j;
			}*/
			System.out.println(reduce);
			final long l = XxxUtils.end();
			if (l < fastest) {
				fastest = l;
			}
		}
		System.out.println("时间最少得一个：" + fastest);
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
