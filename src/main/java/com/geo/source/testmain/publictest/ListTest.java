package com.geo.source.testmain.publictest;

import com.geo.source.testmain.iterator_class.ArrayIteratorTest;
import com.geo.source.testmain.iterator_class.ListIteratorTest;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class ListTest {

    public static void main(String[] args) {
        m21();
    }

    /**
     * 排序
     */
    private static void m21() {
        List<Aa> aas = new ArrayList<>();
        aas.add(new Aa("1", "1"));
        aas.add(new Aa("3", "3"));
        aas.add(new Aa("2", "2"));
        aas.add(new Aa("4", "4"));

        Map<Integer, Comparator<Aa>> functionMap = new HashMap<Integer, Comparator<Aa>>() {{
            put(1, Comparator.comparing(Aa::getId));
            put(2, Comparator.comparing(Aa::getName));
        }};

        aas.sort(functionMap.get(1).reversed());
        System.out.println(aas);
    }

    /**
     * continue 不会被 catch 掉，但异常会使 continue 失效！
     */
    private static void m20() {
        final List<String> list = Arrays.asList("1", "2", "3");
        for (String s : list) {
            Map<String, String> params = null;
            try {
                params = getStringStringMap(s, params);
                if (params == null) {
                    continue;
                }
            } catch (Exception e) {
                System.err.println("异常");
            }

            System.out.println(params.get("1"));
        }
    }

    private static Map<String, String> getStringStringMap(String s, Map<String, String> params) {
        if ("1".equals(s)) {
            params.get("");
        } else {
            params = new HashMap<>(1);
            params.put(s, s);
        }
        return params;
    }

    private static void m19() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        list.removeIf("B"::equals);
        list.forEach(System.out::println);
    }

    private static void m18() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        for (String s : list) {
            if ("B".equals(s)) {
                list.remove(s);
            }
        }
        list.forEach(System.out::println);
    }

    private static void m17() {
        List<String> asList = Stream.of("", "").collect(Collectors.toList());
        asList.forEach(System.out::println);
    }

    private static void m16() {
        List<String> list = new ArrayList<>();
        List<String> linkedlist = new LinkedList<>();
        for (int i = 0; i < 10000000; i++) {
            list.add("ABC" + i);
            linkedlist.add("ABC" + i);
        }

        XxxUtils.start();
        list.add(9999999, "-ABC0");
        XxxUtils.end();
        System.out.println("==========================================");
        XxxUtils.start();
        linkedlist.add(9999999, "-ABC0");
        XxxUtils.end();

        //list.forEach(System.out::println);
        System.out.println("==========================================");
        System.out.println("==========================================");
        System.out.println(list.size());
        // linkedlist.forEach(System.out::println);
        System.out.println(linkedlist.size());
    }

    private static void m15() {
        long a = 1;
        long sum = 0;
        XxxUtils.start();
        while (a != 100001) {
            sum += a;
            a++;
        }
        XxxUtils.end();
        System.out.println(sum);
        sum = 0;
        XxxUtils.start();
        for (int i = 1; i <= 100000; i++) {
            sum += i;
        }
        XxxUtils.end();
        System.out.println(sum);
    }

    private static void m14() {
        List<String> list = new ArrayList<>();
        list.add("1 ");
        list.add("4 ");
        list.add("3 ");
        list.add("2 ");
        System.out.println(list.subList(0, 3));
        System.out.println(list.subList(2, 3));
    }

    private static void m13() {
        long count = IntStream.range(1, 100).count();
        long count1 = IntStream.rangeClosed(1, 100).count();
        System.out.println(count + ":" + count1);
    }

    private static void m12() {
        List<String> list = new ArrayList<>();
        list.add("1 ");
        list.add("4 ");
        list.add("3 ");
        list.add("2 ");
        List<String> strings = list.stream().filter(s -> s.compareToIgnoreCase("2") >= 0).map(String::trim).collect(Collectors.toList());
        System.out.println(strings);
        List<String> strings1 = list.stream().filter(s -> s.compareToIgnoreCase("2") >= 0).map(String::trim).limit(2).collect(Collectors.toList());
        System.out.println(strings1);
        List<String> strings2 = list.stream().filter(s -> s.compareToIgnoreCase("2") >= 0).collect(Collectors.toList());
        System.out.println(strings2);
        // List<String> strings3 = list.stream().sorted(String::compareToIgnoreCase).collect(Collectors.toList());
        list.sort(String::compareToIgnoreCase);
        System.out.println(list);
    }

    public final int value = 4;

    private static void m11() {
        int value = 6;
        new Runnable() {
            public final int value = 5;

            @Override
            public void run() {
                int value = 10;
                System.out.println(this.value);
            }
        }.run();
        //new ArrayList<>().sort();

    }

    private static void m10() {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        for (int i = 0; i < 2; i++) {
            System.out.println("第" + (i + 1) + "遍");
            for (Integer integer : set) {
                set.clear();
                System.out.println("value:" + integer);
            }
        }
    }

    private static void method9() {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        Iterator<Integer> iterator = set.iterator();
        for (int i = 0; i < 2; i++) {
            System.out.println("第" + (i + 1) + "遍");
            while (iterator.hasNext()) {
                Integer next = iterator.next();
                iterator.remove();
                System.out.println("value:" + next);
            }
        }
    }

    /**
     * 测试标签
     * <p>
     * 2018-12-19 10:25:02 void
     */
    private static void method8() {
        boolean b = true;
        int i = 1;
        flag:
        if (b) {
            System.out.println("进来" + (i++) + "次！");

            break flag;
        }
    }

    /**
     * 测试 break
     * <p>
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
        Integer[] i = {1, 2, 3, 4, 5};

        Iterator<Integer> iteratorTest = new ArrayIteratorTest<>(i);
        while (iteratorTest.hasNext()) {
            Integer integer = (Integer) iteratorTest.next();
            if (integer == 2) {
                iteratorTest.remove();
            }
        }

        System.out.println(iteratorTest.toString());
    }

    private static void method1(int value) {
        List<String> list = new LinkedList<String>();
        for (int i = 0; i < 1000000; i++) {
            list.add("a" + i);
        }

        long start = System.currentTimeMillis();
        for (String s : list) {
            if (s.compareTo("a999999") == 0) {
                System.out.println(s);
                break;
            }
        }
        System.out.println(value + "外部迭代所需时间：" + (System.currentTimeMillis() - start));

        long start2 = System.currentTimeMillis();
        list.parallelStream().filter(s -> s.compareTo("a999999") == 0).limit(1).forEach(System.out::println);
        System.out.println(value + "内部迭代所需时间：" + (System.currentTimeMillis() - start2));

        long start3 = System.currentTimeMillis();
        list.stream().filter(s -> s.compareTo("a999999") == 0).limit(1).forEach(System.out::println);
        System.out.println(value + "内部迭代所需时间：" + (System.currentTimeMillis() - start3));
    }

}
