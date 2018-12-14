package com.geo.source.testmain.publictest;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

public class StringTest {

	public static void main(String[] args) {
		// method1();
		// method2();
		// method4();
		method5();
	}

	private static void method5() {
		String a = "--";
		Object o = "--";
		System.out.println(a.equals(o));
	}

	private static void method4() {
		System.out.println(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
	}

	private static void method2() {
		Letter a = new Letter();
		a.setA("11");
		method3(a);
		System.out.println(a.toString());
	}

	private static void method3(Letter a) {
		a.setA("22");
	}

	/**
	 * 
	 *
	 * 2018-10-22 14:20:49 void
	 */
	@SuppressWarnings("unused")
	private static void method1() {
		Letter l = new Letter();
		try {
			System.out.println(l.getClass().getMethod("getA", String.class));
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
