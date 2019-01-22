package com.geo.source.head_first.design_mode.singletonpattern;

import com.geo.source.testmain.publictest.Singleton;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadFactory;

/**
 * @Description 单例模式测试
 * @Author YanZhen
 * 2019-01-17 15:11
 */
public class SingletonPatternMain {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        long l = System.currentTimeMillis();

        /*for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                singleton1();
                countDownLatch.countDown();
            }).start();
        }*/
        // System.out.println(">>>>>>>>>>>>>>>>>>>>>\n");
        /*for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                singleton2();
                countDownLatch.countDown();
            }).start();
        }*/
        // System.out.println(">>>>>>>>>>>>>>>>>>>>>\n");

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                singleton3();
                countDownLatch.countDown();
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("耗费时长：" + (System.currentTimeMillis() - l));
    }

    /**
     * 双重加锁单例模式
     */
    private static void singleton3() {
        SingletonPattern3 singletonPattern3 = SingletonPattern3.getInstance();
        singletonPattern3.getParam();
        singletonPattern3.getParam();
    }

    /**
     * 急切
     */
    private static void singleton2() {
        SingletonPattern2 singletonPattern2 = SingletonPattern2.getInstance();
        singletonPattern2.getParam();
        singletonPattern2.getParam();
    }

    /**
     * 同步
     */
    private static void singleton1() {
        SingletonPattern1 singletonPattern1 = SingletonPattern1.getInstance();
        singletonPattern1.getParam();
        singletonPattern1.getParam();
    }


}
