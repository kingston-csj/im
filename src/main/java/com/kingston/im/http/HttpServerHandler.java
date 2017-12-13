package com.kingston.im.http;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.kingston.im.net.ChannelUtils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.util.CharsetUtil;

public class HttpServerHandler extends ChannelHandlerAdapter {

	private Logger logger = LoggerFactory.getLogger(HttpServerHandler.class);

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		String ipAddr = ChannelUtils.getIp(channel);
	}

	@Override
	public void channelRead(ChannelHandlerContext context,Object msg)
			throws Exception {
		FullHttpRequest httpRequest = (FullHttpRequest)msg;
		Channel channel = context.channel();
		String ipAddr = ChannelUtils.getIp(channel);

		if (httpRequest.getMethod().equals(HttpMethod.GET)) {
			QueryStringDecoder queryDecoder = new QueryStringDecoder(httpRequest.getUri());
			Map<String, List<String>> params = queryDecoder.parameters();
			logger.info("收到Ip[{}]的http请求,参数为[{}]", ipAddr, params);
		}

		context.writeAndFlush(createResponse(HttpResult.valueOf(HttpResultCode.SUCC)));
	}

	private FullHttpResponse createResponse(HttpResult result) {
		String jsonData = new Gson().toJson(result);
		ByteBuf buf = Unpooled.copiedBuffer(jsonData, CharsetUtil.UTF_8);
		FullHttpResponse response = new DefaultFullHttpResponse(
				HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf);
		response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain");
		response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, 
				buf.readableBytes());

		return response;
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		ctx.close();
	}

}
