package com.geo.source.testmain.publictest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetTest {

	public static void main(String[] args) {
		// mathod1();
		mathod2();
	}

	private static void mathod2() {
		Set<Integer> set = new HashSet<>();
		set.add(10);
		set.add(11);
		set.add(14);
		set.add(10);
		Collection<Integer> c = set;
		List<Integer> list = new ArrayList<>(set);
	}

	private static void mathod1() {
		String a = "1,2,3";
		String[] b = a.split(",");
		System.out.println(b.toString());
		Set<Long> set = new HashSet<>();
		set.contains("");
	}

}
