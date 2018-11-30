package com.geo.source.testmain.publictest;

public class TestStringMain {

	public static void main(String[] args) {
		System.out.println(Topic.ALL[0]);
	}

	private abstract interface Topic {
		// 读取数据
		final String READER_SPOUT = "reader-spout";
		// 创建
		final String CREATOR_BOLT = "creator-bolt";
		// 计数
		final String COUNT_BOLT = "count-bolt";
		
		final String[] ALL = new String[] {READER_SPOUT, CREATOR_BOLT, COUNT_BOLT};
	}
}
