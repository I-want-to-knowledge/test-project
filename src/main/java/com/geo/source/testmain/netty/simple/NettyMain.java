package com.geo.source.testmain.netty.simple;

/**
 * 启动
 *
 * @author YanZhen
 * 2018-11-10 15:00:54
 * NettyMain
 */
public class NettyMain {

	/**
	 * 启动后cmd输入：Telnet 127.0.0.1 8080
	 * 然后输入  Ctrl+]  然后在cmd（sen **）随便输入，看jvm控制台是否打印出文字
	 *
	 * 2018-11-12 19:32:29
	 * @param args
	 * @throws InterruptedException void
	 */
	public static void main(String[] args) throws InterruptedException {
		int port;
		if (args.length > 0) {
			port = Integer.valueOf(args[0]);
		} else {
			port = 8080;
		}
		
		new DiscardServer(port).run();
		System.out.println("server:run()");
	}

}
