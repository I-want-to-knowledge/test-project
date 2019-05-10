package com.geo.source.testmain.lock_test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁
 *
 * @author YanZhen
 * @since 2019-05-10 13:44
 */
public class ReentrantLockTest {
  private static final ReentrantLock lock = new ReentrantLock();
  private static final CountDownLatch c = new CountDownLatch(100);
  private static int a = 0;
  public static void main(String[] args) {
    m1();
  }

  private static void m1() {
    for (int i=0;i<100;i++) {
      c.countDown();
      final Thread thread = new Thread(() -> {
        try {
          c.await();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        lock.lock();
        // System.out.println("锁1");
        lock.lock();
        a++;
        System.out.println(lock.getHoldCount() + "--"+a);
        setA(a);
        lock.unlock();
        // lock.unlock();
      });
      thread.start();
    }

    // System.out.println(getA());
  }

  public static int getA() {
    return a;
  }

  public static void setA(int a) {
    ReentrantLockTest.a = a;
  }
}
