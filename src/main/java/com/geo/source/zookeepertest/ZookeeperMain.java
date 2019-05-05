package com.geo.source.zookeepertest;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.zookeeper.KeeperException;

public class ZookeeperMain {
	// create static instance for ZookeeperTest class.
	private static ZookeeperTest zkt = new ZookeeperTest();
	private static ZookeeperMathod zkm;

	public static void main(String[] args) {
		// znode path
		String path = "/MyFirstZnode";
		String path2 = "/MyLastZnode";

		// data in byte array
		byte[] data = "My first zookeeper app".getBytes(Charset.forName("utf-8"));
		byte[] data2 = "My last zookeeper app".getBytes(Charset.forName("utf-8"));

		try {
			zkm = new ZookeeperMathod(zkt.connect("192.168.10.45:2181,192.168.10.69:2181,192.168.10.101:2181"));

			method(zkm, path, path2, data, data2);

			zkt.cols();
		} catch (Exception e) {
			System.out.println("出现错误：" + e.getMessage());
			e.printStackTrace();
		}

	}

	private static void method(ZookeeperMathod zkm, String path, String path2, byte[] data, byte[] data2)
			throws KeeperException, InterruptedException, UnsupportedEncodingException {
		/*zkm.create(path, data);*/
		
		/*Stat stat = zkm.exists(path);
		if (stat != null) {
			System.out.println(path + " is version " + stat.getVersion());
		} else {
			System.out.println("node does not exists!");
		}*/

		/*zkm.setData(path, data2);*/
		
		List<String> children = zkm.getChildren(path);
		// print children's
		for (String value : children) {
			System.out.println("node " + value);
			zkm.delete(path + "/" + value);
		}
		
		zkm.delete(path);
		
		byte[] by = zkm.getData(path);
		if (by != null) {
			String str = new String(by, StandardCharsets.UTF_8);
			System.out.println(str);
		}
	}
}
