package com.geo.source.testmain.publictest;

import java.util.HashSet;
import java.util.Set;

/**
 * 数组测试
 *
 * @author YanZhen
 * 2018-11-19 17:34:05
 * ArrayTest
 */
public class ArrayTest {

	public static void main(String[] args) {
		method1();
	}

	// 测试set转array
	private static void method1() {
		Set<String> set = new HashSet<>();
		set.add("1");
		set.add("2");
		set.add("3");
		String[] array = set.toArray(new String[]{});
		for (String string : array) {
			System.out.println(string);
		}
	}

}
