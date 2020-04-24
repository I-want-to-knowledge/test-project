package com.geo.source.testmain.publictest;

/**
 * @author YanZhen
 * @date 2020/04/14 12:47
 **/
public class BitTest {
    public static void main(String[] args) {

    }

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

        while(bits > 0) {
            // 每次从当前行可用的格子中取出最右边位为 1 的格子放置皇后
            p = bits & -bits;

            // 紧接着在下一行继续放皇后
            queenSettle(row+1, column | p, (pie|p) << 1, (na | p) >> 1);

            // 当前行最右边格子已经选完了，将其置成 0，代表这个格子已遍历过
            bits = bits & (bits-1);
        }
    }
}
