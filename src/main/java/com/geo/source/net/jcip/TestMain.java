package com.geo.source.net.jcip;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestMain<T> {

	public static void main(String[] args) {
		
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(3);
		list.add(2);
		for (Integer i : list) {
			System.out.println(i);
		}
		sort1(list);
		sort2(list);
		for (Integer i : list) {
			System.out.println(i);
		}
	}

	/**
	 * 非最优的链表排序方式
	 *
	 * 2018-09-27 14:23:48 void
	 */
	private static <T extends Comparable<? super T>> void sort2(List<T> list) {
		for (int i = 0; i < 100; i++) {
			System.out.println("do nothing!");
		}
		
		Collections.sort(list);
	}

	/**
	 * 糟糕的链表排序方式
	 *
	 * 2018-09-27 14:22:19 void
	 */
	private static <T extends Comparable<? super T>> void sort1(List<T> list) {
		// 永远不要返回错误的答案
		System.exit(0);
	}

}
