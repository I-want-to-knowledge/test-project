package com.geo.source.testmain.netty.simple1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;

import com.alibaba.fastjson.JSONObject;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.multipart.MemoryAttribute;
import io.netty.util.CharsetUtil;

/**
 * netty 服务器处理程序
 *
 * @author YanZhen
 * 2018-11-17 14:44:02
 * NettyHttpServerHandler
 */
public class NettyHttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
	
	// 处理请求
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
		System.out.println(request);
		
		// 响应数据
		FullHttpResponse response = null;
		if (request.method() == HttpMethod.GET) {
			System.out.println(getGetParamsFromChannel(request));
			ByteBuf buf = Unpooled.copiedBuffer("GET method over!", CharsetUtil.UTF_8);
			response = responseOk(HttpResponseStatus.OK, buf);
		} else if (request.method() == HttpMethod.POST) {
			System.out.println(getPostParamsFromChannel(request));
			ByteBuf buf = Unpooled.copiedBuffer("GET method over!", CharsetUtil.UTF_8);
			response = responseOk(HttpResponseStatus.OK, buf);
		} else {
			response = responseOk(HttpResponseStatus.INTERNAL_SERVER_ERROR, null);
		}
		
		// ctx.write("成功！");
		// 发送响应
		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// super.exceptionCaught(ctx, cause);
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
	}
	
	/**
	 * 处理post请求
	 *
	 * 2018-11-17 15:26:15
	 * @param request
	 * @return Map<String, Object>
	 */
	private Map<String, Object> getPostParamsFromChannel(FullHttpRequest request) {
		if (request.method() == HttpMethod.POST) {
			String strContentType = request.headers().get("Content-Type").toString().trim();
			if (strContentType.contains("x-www-form-urlencoded")) {
				return getFormParams(request);
			} else if (strContentType.contains("application/json")) {
				return getJsonParams(request);
			}
		}
		return null;
	}

	/**
	 * 解析‘json’请求数据
	 *
	 * 2018-11-17 15:54:40
	 * @param request
	 * @return Map<String,Object>
	 */
	private Map<String, Object> getJsonParams(FullHttpRequest request) {
		Map<String, Object> params =new HashMap<>();
		ByteBuf content = request.content();
		byte[] reqContent = new byte[content.readableBytes()];
		content.readBytes(reqContent);
		String strContent = new String(reqContent, CharsetUtil.UTF_8);
		JSONObject parse = JSONObject.parseObject(strContent);
		for (String key : parse.keySet()) {
			params.put(key, parse.get(key));
		}
		return params;
	}

	/**
	 * 解析‘x-www-form-urlencoded’请求数据
	 *
	 * 2018-11-17 15:37:54
	 * @param request
	 * @return Map<String,Object>
	 */
	private Map<String, Object> getFormParams(FullHttpRequest request) {
		Map<String, Object> params =new HashMap<>();
		HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(new DefaultHttpDataFactory(false), request, CharsetUtil.UTF_8);
		List<InterfaceHttpData> bodyHttpDatas = decoder.getBodyHttpDatas();
		for (InterfaceHttpData interfaceHttpData : bodyHttpDatas) {
			if (interfaceHttpData.getHttpDataType() == InterfaceHttpData.HttpDataType.Attribute) {
				MemoryAttribute attri = (MemoryAttribute) interfaceHttpData;
				params.put(attri.getName(), attri.getValue());
			}
		}
		return params;
	}

	/**
	 * get请求数据解析
	 *
	 * 2018-11-17 14:58:33
	 * @param msg
	 * @return Map<String, Object>
	 */
	private Map<String, Object> getGetParamsFromChannel(FullHttpRequest request) {
		Map<String, Object> params =new HashMap<>();
		if (request.method() == HttpMethod.GET) {
			QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
			Map<String, List<String>> parameters = decoder.parameters();
			if (parameters == null) {
				return null;
			}
			for (String key : parameters.keySet()) {
				List<String> list = parameters.get(key);
				// System.out.println(list);
				params.put(key, list);
			}
		}
		return params;
	}
	
	/**
	 * 返回
	 *
	 * 2018-11-17 15:23:46
	 * @param ok
	 * @param buf
	 * @return FullHttpResponse
	 */
	private FullHttpResponse responseOk(HttpResponseStatus ok, ByteBuf buf) {
		FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, ok, buf);
		if (buf != null) {
			response.headers().set(HttpHeaders.CONTENT_TYPE, "text/plain;charset=UTF-8");
			response.headers().set(HttpHeaders.CONTENT_LENGTH, response.content().readableBytes()+"");
		}
		return response;
	}

}
