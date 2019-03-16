package com.geo.source.testmain.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 传说中：基数排序
 *
 * 时间复杂度：O(nd)
 * 空间复杂度：O(n+k)（其中k是整数的范围）
 *
 * 最大值和最小值差距尽可能小
 *
 * @author YanZhen
 * @since 2019-03-16 09:22
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {10, 58, 32, 56, 57, 99, 37, 46, 75, 89, 12, 11, 19, 16, 45, 5};
        System.out.println("个位排序");
        sort(arr, 1);
        /*for (int i : arr) {
            System.out.print(i + "/");
        }
        System.out.println();*/
        System.out.println("十位排序");
        sort(arr, 2);
        System.out.println("完成基数排序");

    }

    /**
     * 排序
     * @param arr 要排序的数据
     * @param b 数据的最大位数，这里只能是小于100的数
     */
    private static void sort(int[] arr, int b) {
        Map<Integer, List<Integer>> map = new HashMap<>();// 准备一个桶容器
        // 准备10个桶
        for (int i = 0; i < 10; i++) {
            // 一个桶里可以装多个
            List<Integer> buckets = new ArrayList<>();
            // 往桶里扔
            for (int a : arr) {
                // 判断是个位基数还是十位基数
                int v = Utils.digit(a, b);
                // 找到对应的基数
                if (v == i) {
                    buckets.add(a);
                    map.put(i, buckets);
                }
            }
        }

        int j = 0;
        // 桶里取出放回原容器
        for (int i = 0; i < 10; i++) {
            List<Integer> list = map.get(i);
            if (list == null) {
                continue;
            }
            for (Integer a : list) {
                arr[j++] = a;
                System.out.print(a + "/");
            }
        }

        System.out.println();
    }

}
