package com.geo.source.testmain.publictest.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程原子递增
 * @author YanZhen
 * @date 2020/09/28 13:12
 */
public class AtomicTest {
    private static AtomicInteger aInt = new AtomicInteger();
    public static final int THREADS_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];

        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    increase();
                }
            });
            threads[i].start();
        }

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }

        System.out.println(aInt);
    }

    public static void increase() {
        aInt.incrementAndGet();
    }
}
