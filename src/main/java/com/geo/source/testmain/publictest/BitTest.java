package com.geo.source.testmain.publictest;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.kafka.common.utils.ByteUtils;
import org.apache.kafka.common.utils.Bytes;
import sun.security.util.BitArray;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * @author YanZhen
 * @date 2020/04/14 12:47
 **/
public class BitTest {
    public static void main(String[] args) {
        // bitArray();
        numToBit();
    }

    /**
     * 数值转二进制
     */
    private static void numToBit() {
        // 本月历史数据
        int var = 15;
        BitArray bitArray = intToBit(var);
        System.out.println(bitArray.toString());
//        System.out.println(Arrays.toString(bitArray.toByteArray()));
//        System.out.println(Arrays.toString(bitArray.toBooleanArray()));
        // 签到
        int dayOfMonth = LocalDate.now().getDayOfMonth();
        bitArray.set(dayOfMonth, true);
        System.out.println(bitArray.toString());
    }

    /**
     * @param var
     * @return
     */
    private static BitArray intToBit(int var) {
        int count = LocalDate.now().lengthOfMonth();
        BitArray bitArray = new BitArray(count);
        for (int i = 0; i < count; i++) {
            bitArray.set(i, (var & 1) > 0);
            var >>>= 1;
        }
        return bitArray;
    }


    /**
     * 测试bitArray
     */
    private static void bitArray() {
        boolean[] bs = {false, true, false, false, false, false, false, false};
        BitArray array = new BitArray(bs);
        boolean b = array.get(1);
        System.out.println(b);
        System.out.println(16 >> 2);
        System.out.println(16 >>> 2);
        System.out.println(Integer.toBinaryString(8));
    }

    /**
     * 八皇后共存
     *
     * @param row
     * @param column
     * @param pie
     * @param na
     */
    static void queenSettle(int row, int column, int pie, int na) {
        int N = 8; // 8皇后
        int count = 0;
        int p = 0;
        if (row >= N) {
            // 遍历到最后一行说明已经找到符合的条件了
            count++;
            return;
        }

        // 取出当前行可放置皇后的格子
        int bits = (~(column | pie | na)) & ((1 << N) - 1);

        while (bits > 0) {
            // 每次从当前行可用的格子中取出最右边位为 1 的格子放置皇后
            p = bits & -bits;

            // 紧接着在下一行继续放皇后
            queenSettle(row + 1, column | p, (pie | p) << 1, (na | p) >> 1);

            // 当前行最右边格子已经选完了，将其置成 0，代表这个格子已遍历过
            bits = bits & (bits - 1);
        }
    }
}
