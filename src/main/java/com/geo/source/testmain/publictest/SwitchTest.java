package com.geo.source.testmain.publictest;

/**
 * Switch test
 *
 * @author YanZhen 2018-12-14 16:17:52 SwitchTest
 */
public class SwitchTest {
	public static void main(String[] args) {
		String a = "5";
		method1(a);
	}

	/**
	 * switch test
	 *
	 * 2018-12-14 16:21:30 void
	 */
	private static void method1(String a) {
		switch (a) {
			case "1":
			case "2":
			case "4":
				System.out.println("经过了4！");
			case "5":
			case "6":
			case "7":
				System.out.println("经过了7！");
				break;
			case "3":
				System.out.println("3");
				break;

			default:
				break;
		}
	}
}
