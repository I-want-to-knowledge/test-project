package com.geo.source.testmain.publictest.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程之间等待与释放
 * object的wait、notify
 * @author YanZhen
 * @date 2020/06/25 11:38
 */
public class ThreadTestMain {
    private static AtomicInteger integer = new AtomicInteger(0);

    public static void main(String[] args) {
        CountDownLatch count = new CountDownLatch(6);
        Test test = new Test();
        for (int i = 0; i < 5; i++) {
            count.countDown();
            new Thread(() -> test.m1(integer.getAndAdd(1)), "线程-" + i).start();

            if (i == 4) {
                new Thread(() -> {
                    synchronized (test) {
                        test.notifyAll1();
                    }
                }).start();
            }

            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            count.await(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Test {
        private void m1(int a) {
//            while (true) {
            System.out.println(Thread.currentThread().getName() + "-收到消息：" + a);
            try {
                synchronized (this) {
                    this.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-关闭消息：" + a);
//            }
        }

        public synchronized void notifyAll1() {
            this.notifyAll();
        }
    }
}
