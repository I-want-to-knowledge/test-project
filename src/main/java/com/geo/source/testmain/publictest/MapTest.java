package com.geo.source.testmain.publictest;

import com.google.common.collect.Maps;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class MapTest {

    public static void main(String[] args) {
        // test1();
//		m1();
        // m2();
//        m3();
//        m4();
//        m5();
//        m6();
//        m7();
//        m8();

        // 测试msp的扩容
        m9();
    }

    /**
     * 测试msp的扩容
     */
    private static void m9() {
        Map a = new HashMap();
        for (int i = 0; i < 12; i++) {
            a.put(i, i);
        }

        Map b = new HashMap();
        for (int i = 0; i < 13; i++) {
            b.put(i, i);
        }

        Map c = new HashMap();
        for (int i = 0; i < 24; i++) {
            c.put(i, i);
        }

        Map d = new HashMap();
        for (int i = 0; i < 25; i++) {
            d.put(i, i);
        }

        Map e = new HashMap(5);
        for (int i = 0; i < 5; i++) {
            e.put(i, i);
        }

        Map f = new HashMap(5, 1);
        for (int i = 0; i < 5; i++) {
            f.put(i, i);
        }

        print(a);
        print(b);
        print(c);
        print(d);
        print(e);
        print(f);

    }

    public static void print(Map a) {
        try {
            Class<?> mapType = a.getClass();
            Method capacity = mapType.getDeclaredMethod("capacity");
            capacity.setAccessible(true);
            System.out.println("capacity : " + capacity.invoke(a) + "    size : " + a.size());

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void m8() {
        Map<String, Integer> map = new HashMap<>(3);
        map.put("key1", 1);
        map.put("key2", 2);
        map.put("key3", 3);
        map.put(null, 4);
        System.out.println(map.get(null));
    }

    private static void m7() {
        Map<String, Integer> map = new HashMap<>(3);
        map.put("key1", 1);
        map.put("key2", 2);
        map.put("key3", 3);
        final Map<String, Integer> map1 = new HashMap<>(map);
        for (Map.Entry<String, Integer> o : map1.entrySet()) {
            map.remove("key2");
            System.out.println(o.getValue());
        }

        map.forEach((k, v)-> System.out.println(v));
    }

    private static void m6() {
        Map<String, Integer> map = new HashMap<>(5);
        map.put("key1", 1);
        map.put("key2", 2);
        map.put("key3", 3);
        map.put("key4", 4);
        map.put("key5", 5);

        final Class<? extends Map> aClass = map.getClass();
        final Field[] fields = aClass.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }

    private static void m5() {
        final Map<Long, Long> map = new LinkedHashMap<>(1);
        map.put(1L, 1L);
        map.forEach((key, value) -> map.remove(key));
        System.out.println(map);
    }

    private static void m4() {
        final Map<Long, Long> map = new LinkedHashMap<>(1);
        map.put(1L, 1L);
        for (Map.Entry<Long, Long> entry : map.entrySet()) {
            map.remove(entry.getKey());
        }

        System.out.println(map);
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
