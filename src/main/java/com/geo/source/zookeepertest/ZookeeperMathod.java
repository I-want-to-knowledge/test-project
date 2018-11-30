package com.geo.source.zookeepertest;

import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZookeeperMathod {

	// create instance for ZooKeeper class.
	private ZooKeeper zk;
	// create instance for ZookeeperTest class.
	// private ZookeeperTest zkt;
	public ZookeeperMathod(ZooKeeper zk) {
		this.zk = zk;
	}
	
	// create
	public void create(String path, byte[] data) throws KeeperException, InterruptedException {
		zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
	}
	
	// Method to check existence of node and it's status, if node is available
	public Stat exists(String path) throws KeeperException, InterruptedException {
		return zk.exists(path, true);
	}
	
	public byte[] getData(String path) throws KeeperException, InterruptedException {
		Stat stat = exists(path);
		byte[] data = null;
		if (stat != null) {
			data = zk.getData(path, false, null);
		} else {
			System.out.println("Node does not exists!");
		}
		return data;
	}
	
	/**
	 * method to update the data in a node, similar to getData but without watcher
	 *
	 * 2018-07-11 14:08:18
	 * @param path
	 * @param data
	 * @throws KeeperException
	 * @throws InterruptedException void
	 */
	public void setData(String path, byte[] data) throws KeeperException, InterruptedException {
		zk.setData(path, data, exists(path).getVersion());
	}
	
	/**
	 * method get all the children of node, it has two arguments, path and watcher
	 *
	 * 2018-07-11 14:22:39
	 * @param path
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException List<String>
	 */
	public List<String> getChildren(String path) throws KeeperException, InterruptedException {
		Stat stat = exists(path);
		List<String> children = null;
		if (stat != null) {
			children = zk.getChildren(path, false);
		} else {
			System.out.println("node does not exists!");
		}
		return children;
	}
	
	public void delete(String path) throws InterruptedException, KeeperException {
		zk.delete(path, exists(path).getVersion());
	}
}
