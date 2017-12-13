package com.kingston.im.http;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class HttpServer {
	
	private Logger logger = LoggerFactory.getLogger(HttpServer.class);
	
	private EventLoopGroup bossGroup;
	
	private EventLoopGroup workerGroup;
	
	private List<ChannelFuture> channelFutures = new ArrayList<>();
	
	public void start(int port) throws Exception {
		bossGroup = new NioEventLoopGroup(1);
		workerGroup = new NioEventLoopGroup(1);
		ServerBootstrap b = new ServerBootstrap();
		b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
		 .handler(new LoggingHandler(LogLevel.TRACE))
		 .childHandler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast("http-decorder", new HttpRequestDecoder());
				ch.pipeline().addLast("http-aggregator", new HttpObjectAggregator(512*1024));
				ch.pipeline().addLast("http-encoder", new HttpResponseEncoder());
				ch.pipeline().addLast("serve-handler", new HttpServerHandler());
			}
		});
		
		logger.info("http server start at [{}]", port);
		ChannelFuture future = b.bind(new InetSocketAddress(port)).sync();
		channelFutures.add(future);
	}

}
