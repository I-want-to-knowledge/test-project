package com.geo.source.testmain.publictest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ThreedTest {

	public static void main(String[] args) {
		CountDownLatch countDownLatch = new CountDownLatch(50);
		countDownLatch.countDown();
		try {
			countDownLatch.await(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
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
