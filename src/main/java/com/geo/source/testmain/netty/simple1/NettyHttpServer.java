package com.geo.source.testmain.netty.simple1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * netty 服务器
 *
 * @author YanZhen
 * 2018-11-17 16:24:25
 * NettyHttpServer
 */
public class NettyHttpServer {

	private int port;
	
	public NettyHttpServer(int port) {
		this.port = port;
	}
	
	public int getPort() {
		return port;
	}
	
	// 处理器
	public void run() throws InterruptedException {
		EventLoopGroup parentGroup = new NioEventLoopGroup();
		EventLoopGroup childGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap strap = new ServerBootstrap();
			// 添加两个线程分别处理客户通道的accept和读写事件
			strap.group(parentGroup, childGroup)
			// 绑定服务通道NioServerSocketChannel
			.channel(NioServerSocketChannel.class)
			// 给读写事件的线程添加handler
					.childHandler(
							// ChannelInitializer初始化通道SocketChannel
							new ChannelInitializer<SocketChannel>() {

								@Override
								protected void initChannel(SocketChannel ch) throws Exception {
									// 请求解码器
									ch.pipeline().addLast("http-decoder", new HttpRequestDecoder());
									// 将http消息多部分合成一条http消息
									ch.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65535));
									// 响应转码器
									ch.pipeline().addLast("http-encoder", new HttpResponseDecoder());
									// 解决大码流问题，chunkedWriteHandler，向客户发送HTML5文件
									ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
									// 自定义处理handler
									ch.pipeline().addLast("http-server", new NettyHttpServerHandler());
								}
							});
			
			// 监听端口（服务器host和端口port），同步返回
			// strap.bind(inetHost, inetPort).sync();// 指定服务器
			ChannelFuture future = strap.bind(this.port).sync();
			future.channel().closeFuture().sync();
		} finally {
			childGroup.shutdownGracefully();
			parentGroup.shutdownGracefully();
		}
	}
}
