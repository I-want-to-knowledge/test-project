package com.geo.source.testmain.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

import java.nio.charset.Charset;

/**
 * netty 
 *
 * @author YanZhen
 * 2018-11-10 11:30:28
 * DiscardServiceHandler
 */
public class DiscardServerHandler extends ChannelHandlerAdapter {

	// @Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
//		super.channelRead(ctx, msg);
		try {
			ByteBuf in = (ByteBuf) msg;
			System.out.println(in.toString(Charset.forName("GBK")));
		} finally {
			ReferenceCountUtil.release(msg);
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// super.exceptionCaught(ctx, cause);
		cause.printStackTrace();
		ctx.close();
	}
}
