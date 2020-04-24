package com.geo.source.testmain.publictest.thread;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author YanZhen
 * @date 2020/04/13 13:10
 **/
public class CallableAndFuture {

    public static void main(String[] args) throws Exception {
        // 该方式创建线程池不推荐使用

        // 将Callable包装成FutureTask，FutureTask也是一种Runnable
        MyCallable callable = new MyCallable();
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();

        // get方法会阻塞调用的线程
        Integer sum = futureTask.get();
        System.out.println(Thread.currentThread().getName() + Thread.currentThread().getId() + "=" + sum);
    }

    static class MyCallable implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println(Thread.currentThread().getName() + "\t" + Thread.currentThread().getId() + "\t" + new Date() + " \tstarting...");

            int sum = 0;
            for (int i = 0; i <= 100000; i++) {
                sum += i;
            }
            Thread.sleep(5000);

            System.out.println(Thread.currentThread().getName() + "\t" + Thread.currentThread().getId() + "\t" + new Date() + " \tover...");
            return sum;
        }
    }

}
