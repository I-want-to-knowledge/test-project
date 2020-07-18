package com.geo.source.testmain.publictest;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadTest {
  private ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(2, r -> {
    Thread t = new Thread(r, "my-thread-" + r.hashCode());
    System.out.println(t.getName() + " has been created");
    return t;
  });

  public static void main(String[] args) {
    // m1();
//    m2();
    m3();
  }

  /**
   * 定时线程池
   */
  private static void m3() {
    ThreadTest threadTest = new ThreadTest();
    threadTest.lanuchTimer();
    try {
      Thread.sleep(1000*5);//5秒钟之后添加新任务
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    threadTest.addOneTask2();
    threadTest.addOneTask3();
    threadTest.addOneTask4();
  }

  //启动计时器
  public void lanuchTimer(){
    Runnable task = () -> {
      System.out.println("异常抛出！");
      throw new RuntimeException("异常！");
    };
    poolExecutor.scheduleWithFixedDelay(task, 1000*5, 1000*10, TimeUnit.MILLISECONDS);
  }
  //添加新任务
  public void addOneTask2(){
    Runnable task = () -> System.out.println(LocalDateTime.now().toString() + " [" + Thread.currentThread().getName() + "] : 添加的任务2");
    poolExecutor.scheduleWithFixedDelay(task, 10000, 5000, TimeUnit.MILLISECONDS);
  }
  //添加新任务
  public void addOneTask3(){
    Runnable task = () -> System.out.println(LocalDateTime.now().toString() + " [" + Thread.currentThread().getName() + "] : 添加的任务3");
    poolExecutor.scheduleWithFixedDelay(task, 1000, 1000, TimeUnit.MILLISECONDS);
  }
  //添加新任务
  public void addOneTask4(){
    Runnable task = () -> System.out.println(LocalDateTime.now().toString() + " [" + Thread.currentThread().getName() + "] : 添加的任务，看看是否只执行一遍！");
    poolExecutor.execute(task);
    Callable<String> task2 = () -> LocalDateTime.now().toString() + " [" + Thread.currentThread().getName() + "] : 添加的任务，看看是否只执行一遍！";
    Future<String> submit = poolExecutor.submit(task2);
//    if (submit.isDone()) {
      try {
        System.out.println("------------->返回结果：" + submit.get());
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
//    }
  }

  /**
   * ThreadLocal线程间共享值
   */
  private static void m2() {
    /*System.out.println("初始值1：" + ThreadId.nextId.get());
    for (int i = 0; i < 10; i++) {
      new Thread(() -> System.out.println(Thread.currentThread().getName() + ":" + ThreadId.get())).start();
    }
    System.out.println("初始值2：" + ThreadId.nextId.get());*/

    System.out.println("初始值1：" + ThreadId.get());
    for (int i = 0; i < 10; i++) {
      // new ThreadPoolExecutor(1, 100, 500, TimeUnit.MILLISECONDS, null, new ThreadFactory());
      final Thread thread = new Thread(() -> {
        ThreadId.set();
        System.out.println(Thread.currentThread().getName() + ":" + ThreadId.get());
      });
      thread.start();
      /*try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }*/
    }
    System.out.println("初始值2：" + ThreadId.get());
  }

  private static class ThreadId {
    // 第一种实现
    /*// Atomic integer containing the next thread ID to be assigned
    private static final AtomicInteger nextId = new AtomicInteger(0);

    // Thread local variable containing each thread's ID
    private static final ThreadLocal<Integer> threadId =
            new ThreadLocal<Integer>() {
              @Override
              protected Integer initialValue() {
                return nextId.getAndIncrement();
              }
            };

    // Returns the current thread's unique ID, assigning it if necessary
    public static int get() {
      return threadId.get();
    }*/

    // 第二种实现
    static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
      @Override
      protected Integer initialValue() {
        return 0;
      }
    };

    public static Integer get() {
      return threadLocal.get();
    }

    public static void set() {
      threadLocal.set(threadLocal.get() + 1);
    }
  }

  private static void m1() {
    CountDownLatch countDownLatch = new CountDownLatch(50);
    countDownLatch.countDown();
    try {
      countDownLatch.await(1, TimeUnit.HOURS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void thread() {
    if (Thread.currentThread().isInterrupted()) {
      System.out.println("线程中断！！！");
    }

    // test
		/*new Thread(new Runnable() {

			@Override
			public void run() {
				if (hoperations.putIfAbsent("test1", "test2", 1)) {
					logger.info("测试数据，说明存入成功！（1）");
				} else {
					logger.info("测试数据，说明存入失败！（1）");
				}

			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				if (hoperations.putIfAbsent("test1", "test2", 1)) {
					logger.info("测试数据，说明存入成功！（2）");
				} else {
					logger.info("测试数据，说明存入失败！（2）");
				}

			}
		}).start();
		logger.info("测试值：{}", hoperations.hasKey("test1", "test2"));
		logger.info("测试删除：{}", hoperations.delete("test1", "test2"));*/
  }
}
