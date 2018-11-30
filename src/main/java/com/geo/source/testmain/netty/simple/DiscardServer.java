package com.geo.source.testmain.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 启动discard server handler
 *
 * @author YanZhen
 * 2018-11-10 11:44:29
 * DiscardServer
 */
public class DiscardServer {

	private int port;
	public DiscardServer(int port) {
		super();
		this.port = port;
	}

	public void run() throws InterruptedException {

		// 接收进来的链接
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		// 处理接收的链接
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		System.out.println("端口号：" + port);
		
		try {
			// 启动nio的服务类
			ServerBootstrap strap = new ServerBootstrap();
			strap = strap.group(bossGroup, workerGroup);
			
			// 以nio的selector为基础进行实现，用来接收新的链接，告诉channel如何获取新的链接
			strap = strap.channel(NioServerSocketChannel.class);
			
			// 处理接收的channel
			strap = strap.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new DiscardServerHandler());
				}
			});
			
			strap = strap.option(ChannelOption.SO_BACKLOG, 128);
			strap = strap.childOption(ChannelOption.SO_KEEPALIVE, true);
			
			// 绑定接口，并启动接收进来的链接
			ChannelFuture sync = strap.bind(port).sync();
			// 等待，直到socket关闭
			sync.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
		
	}
}
