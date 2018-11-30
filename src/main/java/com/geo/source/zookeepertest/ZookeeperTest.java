package com.geo.source.zookeepertest;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

public class ZookeeperTest {
	// declare zookeeper instance to access ZooKeeper ensemble
	private ZooKeeper zoo;
	final CountDownLatch connectedSignal = new CountDownLatch(1);
	
	/**
	 * Method to connect zookeeper ensemble.
	 *
	 * 2018-07-10 18:34:56
	 * @param host
	 * @return
	 * @throws IOException
	 * @throws InterruptedException ZooKeeper
	 */
	public ZooKeeper connect(String host) throws IOException, InterruptedException {
		// connect
		zoo = new ZooKeeper(host, 5000, we -> {
			if (we.getState() == KeeperState.SyncConnected) {
				connectedSignal.countDown();
			}
			
			System.out.println(we);
		});
		
		connectedSignal.await();
		return zoo;
	}
	
	/**
	 *  Method to disconnect from zookeeper server
	 *
	 * 2018-07-10 18:34:47
	 * @throws InterruptedException void
	 */
	public void cols() throws InterruptedException {
		zoo.close();
	}
}
