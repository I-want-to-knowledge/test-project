package com.geo.source.testmain.publictest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.geo.source.testmain.iterator_class.ArrayIteratorTest;
import com.geo.source.testmain.iterator_class.ListIteratorTest;

@SuppressWarnings("unused")
public class ListTest {

	public static void main(String[] args) {
		// Collections.addAll(null);
		// method4();
		// method5();
		// method6();
		// method7();
		method8();
	}

	/**
	 * 测试标签
	 *
	 * 2018-12-19 10:25:02 void
	 */
	private static void method8() {
		boolean b = true;
		int i = 1;
		flag:if (b) {
			System.out.println("进来" + (i++) +"次！");
			
			break flag;
		}
	}

	/**
	 * 测试 break
	 *
	 * 2018-12-13 10:27:42 void
	 */
	private static void method7() {
		for (int i = 0; i < 2; i++) {
			System.out.println("1");
			for (int j = 0; j < 2; j++) {
				System.out.println("2");
				if (j == 0) break;
				System.out.println("3");
			}
			System.out.println("4");
		}
	}

	private static void method6() {
		List<Set<String>> docs = new ArrayList<>();
		boolean addAll = docs.addAll(Collections.emptyList());
		boolean addAll2 = docs.addAll(Collections.emptyList());
		if (!addAll) {
			docs.add(new HashSet<>());
		}
		if (!addAll2) {
			docs.add(new HashSet<>());
		}
		if (docs == null) {
			System.out.println("无值!");
		}
		System.out.println(addAll);
		System.out.println(addAll2);
		
		for (Set<String> string : docs) {
			System.out.println("v:" + string);
		}
	}

	private static void method5() {
		ArrayList<String> l = new ArrayList<>();
		l.add("1");
		l.add("2");
		l.add("3");
		l.add("4");
		l.add("3");
		
		while (true) {
			if (!l.remove("3"))
				break;
		}
		for (String string : l) {
			System.out.println(string);
		}
	}

	private static void method4() {
		List<String> list = new ArrayList<>();
		for (String string : list) {
			System.out.println(string);
		}
		System.out.println(000);
	}

	private static void method3() {
		List<Integer> nums = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			nums.add(i);
		}
		
		Iterator<Integer> iterator = new ListIteratorTest<>(nums);
		while (iterator.hasNext()) {
			Integer integer = (Integer) iterator.next();
			if (integer == 2) {
				iterator.remove();
			}
		}
		
		System.out.println(iterator.toString());
	}

	private static void method2() {
		Integer[] i = {1,2,3,4,5};
		
		Iterator<Integer> iteratorTest = new ArrayIteratorTest<>(i);
		while (iteratorTest.hasNext()) {
			Integer integer = (Integer) iteratorTest.next();
			if (integer == 2) {
				iteratorTest.remove();
			}
		}
		
		System.out.println(iteratorTest.toString());
	}

	private static void method1() {
		
		/*
		 * List<String> list = new ArrayList<>(); list.contains("");
		 * Arrays.asList("").contains("");
		 */
		
		/*List<String> list = new LinkedList<String>();
		for (int i = 0; i < 5000; i++) {
			list.add("xiaotang");
		}

		long start = System.currentTimeMillis();
		int size = list.size();
		String str = null;
		for (int i = 0; i < size; i++) {	
			str = list.get(i);
		}
		System.out.println("for + get(i)方法: " + (System.currentTimeMillis() - start));

		long start2 = System.currentTimeMillis();
		for (String str1 : list) {
		}
		System.out.println("Iterator(foreach)方法:" + (System.currentTimeMillis() - start2));*/
		
		/*
		 * List<String> list = new ArrayList<String>(); for (int i = 0; i <
		 * 50000000; i++) { list.add("xiaotang"); }
		 * 
		 * long start = System.currentTimeMillis(); int size = list.size(); String
		 * str = null; for (int i = 0; i < size; i++) { str = list.get(i); }
		 * System.out.println("for + get(i)方法: " + (System.currentTimeMillis() -
		 * start));
		 * 
		 * long start2 = System.currentTimeMillis(); for (String str1 : list) { }
		 * System.out.println("Iterator(foreach)方法:" + (System.currentTimeMillis() -
		 * start2));
		 * 
		 * long start3 = System.currentTimeMillis(); list.forEach((l) -> {});
		 * System.out.println("(.foreach)方法:" + (System.currentTimeMillis() -
		 * start3));
		 * 
		 * long start4 = System.currentTimeMillis(); Iterator<String> iterator =
		 * list.iterator(); while (iterator.hasNext()) { String next =
		 * iterator.next(); } System.out.println("(while.hasNext)方法:" +
		 * (System.currentTimeMillis() - start4));
		 */
		
	}

}
