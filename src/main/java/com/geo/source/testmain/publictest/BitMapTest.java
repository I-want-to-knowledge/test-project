package com.geo.source.testmain.publictest;

/**
 * 4G内存，20亿个正整数去重排序
 *
 * @author YanZhen
 * @date 2020/08/31 17:05
 */
public class BitMapTest {

    /**
     * 20亿数据最大数
     */
    private static long MAX_NUM = 20_000_000_000L;
    /**
     * 模拟20亿数据
     */
    private static long[] ARR = {MAX_NUM, 2_000_000_000, 200_000_000, 20_000_000, 2_000_000, 200_000, 200_000, 20_000, 2_000, 200, 20, 2, 0};

    public static void main(String[] args) {
        // 获取bit数组初始大小
        byte[] nums = getBitArray();

        // 检查20亿数字中是否存在该数字
        if (check(nums, MAX_NUM)) {
            System.out.println("存在：" + MAX_NUM);
        }

        // 进行结果匹配

        // 结果打印
    }

    /**
     * 检查是否存在
     * @param nums
     * @param maxNum
     * @return
     */
    private static boolean check(byte[] nums, long maxNum) {
        // todo 方法体完善
        return false;
    }

    private static byte[] getBitArray() {
        // 一个byte=8bit，一个bit是一个桶，一个桶装一个数字，装数字的桶为1，没装为0
        byte[] bs = new byte[(int) MAX_NUM / 8 + 1];
        // 往桶里放数据
        for (long l : ARR) {
            // 找桶所在的byte
            int position = position(l);
            // 找byte中桶的位置
            int i = bytePosition(l);
            // 找到桶的位置，桶的标志改为1，表示数字已经放入
            bs[position] |= 1 << i;
        }
        return bs;
    }

    /**
     * 找byte中桶的位置
     *
     * @param l 数字
     */
    private static int bytePosition(long l) {
        return (int) (l % 8);
    }

    /**
     * 桶所在的byte
     */
    private static int position(long l) {
        return (int) (l / 8 + 1);
    }
}
