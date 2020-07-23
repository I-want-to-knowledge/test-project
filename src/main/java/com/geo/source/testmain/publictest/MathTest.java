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
//		m6();
//		m7();
//		m8();
//		m9();
//		m10();
//		M11();
//		m12();
//		m13();
//		m14();
//		m15();
		m16(5);
	}

	/**
	 * 获取map的容量
	 * 最多1万个
	 */
	private static void m16(int a) {
		int n = a - 1;
		n |= n >>> 1;
//		System.out.println(n);
		n |= n >>> 2;
//		System.out.println(n);
		n |= n >>> 4;
//		System.out.println(n);
		n |= n >>> 8;
//		System.out.println(n);
		n |= n >>> 16;
//		System.out.println(n);
		System.out.println((n < 0) ? 1 : (n >= 10000) ? 10000 : n + 1);
	}

	private static void m15() {
		System.out.println((int) 1 == (short) 1);
	}

	private static void m14() {
		final long i =  ((long)1 << 63) - 1;
		System.out.println(i);
		float f = 9999999999999999999.01f;
		System.out.println(f);
	}

	private static void m13() {
		double d = Double.MAX_VALUE;
		System.out.println("d :" + d);
		d = Double.MIN_VALUE;
		System.out.println("d :" + d);
		float f = Float.MAX_VALUE;
		System.out.println("f :" + f);
		f = Float.MIN_VALUE;
		System.out.println("f :" + f);
		long l = Long.MAX_VALUE;
		System.out.println("l :" + l);
		l = Long.MIN_VALUE;
		System.out.println("l :" + l);
		int i = Integer.MAX_VALUE;
		System.out.println("i :" + i);
		i = Integer.MIN_VALUE;
		System.out.println("i :" + i);
		short s = Short.MAX_VALUE;
		System.out.println("s :" + s);
		s = Short.MIN_VALUE;
		System.out.println("s :" + s);
	}

	private static void m12() {
		int a = 1;
		int b = 1;
		System.out.println(a++);
		System.out.println(++b);
		System.out.println(a);
	}

	private static void M11() {
		System.out.println(getNumber(10L, 2L));
	}

	private static double getNumber(Long number1, Long number2) {
		double prefix = number1 / number2;
		double number3 = number1 / (double) number2;
		double number4 = number3 - (long) number3;
		boolean flag = number4 >= 0.5 && number4 < 1;
		if (flag) {
			prefix += 0.5;
		}
		return prefix;
	}

	private static void m10() {
		final int i = 96355 % 1000;
		System.out.println(i);
	}

	private static void m9() {
		Long.valueOf(null);
	}

	private static void m8() {
		System.out.println(Double.valueOf("-16.1").intValue());
	}

	/**
	 * 相除，进位
	 */
	private static void m7() {
		System.out.println(Math.ceil((float)11/5));
	}

	private static void m6() {
		long a = 1000;
		Object o = a;
		Number number = (Number) o;
		System.out.println(o);
		System.out.println(number);

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
