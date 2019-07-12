package com.geo.source.testmain.publictest;

import com.google.common.collect.Maps;

import java.util.*;

public class MapTest {

    public static void main(String[] args) {
        // test1();
//		m1();
        // m2();
        m3();
    }

    private static void m3() {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        final Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        map.forEach((s, integer) -> System.out.println(s + ":" + integer));
    }

    private static void m2() {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        System.out.println("before=" + map);
        map = Maps.filterKeys(map, p -> Objects.equals(p, "A"));
        System.out.println("after=" + map);
    }

    private static void m1() {
        Map<String, String> map = new HashMap<>();
        map.put(null, "null值");
        System.out.println(map.get(null));
        Map<String, String> tab = new Hashtable<>();
        tab.put(null, "null值");
        System.out.println(tab.get(null));
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
