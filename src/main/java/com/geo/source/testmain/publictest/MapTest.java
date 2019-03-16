package com.geo.source.testmain.publictest;

import javax.swing.text.Document;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapTest {

	public static void main(String[] args) {
		test1();
	}

	private static void test1() {
		Map<Integer, Integer> map = new HashMap<>();
		List<Integer> list = new ArrayList<>();
		int lenth = 10000000;
		for (int i = 0; i < lenth; i++) {
			map.put(i, i);
			list.add(i);
		}
		long start1 = System.currentTimeMillis();
		mathod2(list, 1);
		long end1 = System.currentTimeMillis();
		System.out.println(end1 - start1);

		long start2 = System.currentTimeMillis();
		mathod1(map, 1);
		long end2 = System.currentTimeMillis();
		System.out.println(end2 - start2);
	}

	private static Integer mathod2(List<Integer> list, int l) {
		for (int i = 0; i < list.size(); i++) {
			Integer v = list.get(i);
			if (v == l)
				return v;
		}
		return null;
	}

	private static Integer mathod1(Map<Integer, Integer> map, int i) {
		
		return map.get(i);
	}

}
