package com.geo.source.testmain.sort;

/**
 * 传说中：计数排序
 * 开辟桶的个数：maxValue-minValue+1
 * 扔进桶的数：i-minvalue
 * 取出桶的数：i + minvalue
 *
 * 时间复杂度：Ο(n+maxV-minV)
 * 空间复杂度：O(maxV-minV)
 * 优点：快于任何比较排序算法
 * 缺点：是一种牺牲空间换取时间的做法
 *
 * 计数排序本质上是一种特殊的桶排序，当桶的个数最大的时候，就是计数排序
 * 适用于非负整数
 *
 * @author YanZhen
 * @since 2019-03-16 13:23
 */
public class CountingSort {
    public static void main(String[] args) {
        int[] arr = {10, 58, 32, 56, 57, 99, 37, 46, 75, 89, 12, 11, 19, 16, 45, 5, 2, 40, 88, 66, 33, 77, 2, 33, 99};
        sort(arr);
        for (int i : arr) {
            System.out.print(i + "/");
        }
    }

    /**
     * 排序
     *
     * @param arr 要排序的数字堆
     */
    private static void sort(int[] arr) {
        // 找出最大值和最小值
        int maxvalue = 0;
        int minvalue = 0;
        for (int i : arr) {
            maxvalue = (i > maxvalue ? i : maxvalue);
            minvalue = (i < minvalue ? i : minvalue);
        }

        // 数字堆中都是100以内的数字，所以弄100个桶
        int[] counts = new int[maxvalue - minvalue + 1];// 默认桶内是空的
        /*// 清空桶
        for (int i : arr) {
            counts[i] = 0;
        }*/
        // 遍历数字堆，找出与桶对应的数字，记录出现的次数
        for (int i : arr) {
            counts[i-minvalue]++;
        }

        // 归还数组
        int a = 0;
        for (int i = 0; i < counts.length; i++) {
            int count = counts[i];
            if (count == 0) continue;
            for (int j = 0; j < count; j++) {
                arr[a++] = i + minvalue;
            }
        }
    }
}
