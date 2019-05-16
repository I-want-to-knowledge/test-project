package com.geo.source.testmain.netty.simple1;

/**
 * netty 启动
 * netty http请求及响应
 *
 * @author YanZhen
 * 2018-11-17 17:09:07
 * NettyMain
 */
public class NettyMain {

	public static void main(String[] args) {
		NettyHttpServer nettyHttpServer = new NettyHttpServer(8080);
		try {
			nettyHttpServer.run();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("error:" + e.getMessage());
		}
		
		System.out.println("server close!");
	}

}
