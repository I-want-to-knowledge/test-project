package com.geo.source.testmain.publictest;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author YanZhen
 * @date 2020/10/12 15:24
 */
public class SystemTest {
    public static void main(String[] args) {
//        m1();
//        m2();
        m3();
    }

    private static void m2() {
        System.out.println(System.currentTimeMillis());
    }

    private static void m1() {
        Properties props = System.getProperties();
        System.out.println("Java的运行环境版本：" + props.getProperty("java.version"));
        System.out.println("默认的临时文件路径：" + props.getProperty("java.io.tmpdir"));
        System.out.println("操作系统的名称：" + props.getProperty("os.name"));
        System.out.println("操作系统的构架：" + props.getProperty("os.arch"));
        System.out.println("操作系统的版本：" + props.getProperty("os.version"));
        System.out.println("文件分隔符：" + props.getProperty("file.separator"));   //在 unix 系统中是＂／＂
        System.out.println("路径分隔符：" + props.getProperty("path.separator"));   //在 unix 系统中是＂:＂
        System.out.println("行分隔符：" + props.getProperty("line.separator"));   //在 unix 系统中是＂/n＂
        System.out.println("用户的账户名称：" + props.getProperty("user.name"));
        System.out.println("用户的主目录：" + props.getProperty("user.home"));
        System.out.println("用户的当前工作目录：" + props.getProperty("user.dir"));
        System.out.println("用户的当前工作目录2：" + System.getProperty("user.dir"));
        try {
            InetAddress ip4 = Inet4Address.getLocalHost();
            System.out.println("当前服务器的ip：" + ip4.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String dir = System.getProperty("user.dir");
        String[] split = dir.split("\\\\");
        String s = split[split.length - 1];
        System.out.println("项目文件夹名称：" + s);
    }

    /**
     * 单线程、多线程测试System.currentTimeMillis()的性能
     */
    public static void m3() {
        int num = 10000000;
        System.out.print("单线程" + num + "次System.currentTimeMillis调用总耗时：");
        System.out.println(singleThreadTest(() -> {
            long l = System.currentTimeMillis();
        }, num));
        System.out.print("单线程" + num + "次CacheClock.currentTimeMillis调用总耗时：");
        System.out.println(singleThreadTest(() -> {
            long l = CacheClock.currentTimeMillis();
        }, num));
        System.out.print("并发" + num + "次System.currentTimeMillis调用总耗时：");
        System.out.println(concurrentTest(() -> {
            long l = System.currentTimeMillis();
        }, num));
        System.out.print("并发" + num + "次CacheClock.currentTimeMillis调用总耗时：");
        System.out.println(concurrentTest(() -> {
            long l = CacheClock.currentTimeMillis();
        }, num));
    }

    /**
     * 单线程测试
     *
     * @return 时间
     */
    private static long singleThreadTest(Runnable runnable, int num) {
        long sum = 0;
        for (int i = 0; i < num; i++) {
            long begin = System.nanoTime();
            runnable.run();
            long end = System.nanoTime();
            sum += end - begin;
        }
        return sum;
    }

    /**
     * 并发测试
     *
     * @return 时间
     */
    private static long concurrentTest(Runnable runnable, int num) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(200, 200, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(num));
        long[] sum = new long[]{0};
        //闭锁基于CAS实现，并不适合当前的计算密集型场景，可能导致等待时间较长
        CountDownLatch countDownLatch = new CountDownLatch(num);
        for (int i = 0; i < num; i++) {
            threadPoolExecutor.submit(() -> {
                long begin = System.nanoTime();
                runnable.run();
                long end = System.nanoTime();
                //计算复杂型场景更适合使用悲观锁
                synchronized (SystemTest.class) {
                    sum[0] += end - begin;
                }
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return sum[0];
    }


    /**
     * 缓存时钟，缓存System.currentTimeMillis()的值，每隔20ms更新一次
     */
    public static class CacheClock {
        //定时任务调度线程池
        private static final ScheduledExecutorService timer = new ScheduledThreadPoolExecutor(1);
        //毫秒缓存
        private static volatile long timeMilis;

        static {
            //每秒更新毫秒缓存
            timer.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    timeMilis = System.currentTimeMillis();
                }
            }, 0, 1000, TimeUnit.MILLISECONDS);
        }

        public static long currentTimeMillis() {
            return timeMilis;
        }
    }


}
