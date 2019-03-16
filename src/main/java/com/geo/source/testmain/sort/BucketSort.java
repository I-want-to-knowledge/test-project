package com.geo.source.testmain.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 传说中：桶排序
 * 时间复杂度：O(n+k)（其中k是整数的范围）
 * 空间复杂度：O(n+k)（其中k是整数的范围）
 * 元素尽可能均匀分布
 *
 * 桶越多，时间效率就越高，而桶越多，空间就越大
 * @author YanZhen
 * @since 2019-03-16 10:58
 */
public class BucketSort {
    public static void main(String[] args) {
        int[] arr = {10, 10, 58, 32, 56, 57, 99, 37, 46, 75, 89, 12, 11, 19, 16, 45, 5, 1, 40, 88, 66, 33};
        sort(arr);
        for (int i : arr) {
            System.out.print(i + "/");
        }
        System.out.println("\n桶排序完成");
    }

    /**
     * 排序
     * @param arr 数字堆
     */
    private static void sort(int[] arr) {
        // 准备一些桶，桶容量为10个数字
        Map<Integer, List<Integer>> map = new HashMap<>();
        // 100以内的数字就分10个桶
        for (int i : arr) {
            // 取出十位上的数字
            int digit = Utils.digit(i, 2);
            // 检查是否已经有该数字区间的桶
            List<Integer> list = map.get(digit);
            if (list == null) {
                // 没有就新拿一个
                list = new ArrayList<>();
            }
            // 把数字扔进桶里
            list.add(i);
            list.sort(Integer::compareTo);
            map.put(digit, list);// 把桶标上号
        }

        // 覆盖原数字堆
        int j = 0;
        for (Integer integer : map.keySet()) {
            List<Integer> values = map.get(integer);
            for (Integer value : values) {
                arr[j++] = value;
            }
        }
    }

}
