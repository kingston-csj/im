package com.kingston.im.http;

import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kingston.im.ServerConfigs;
import com.kingston.im.base.ServerNode;
import com.kingston.im.base.SpringContext;

import io.netty.bootstrap.ServerBootstrap;
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

public class HttpServer implements ServerNode {

	private Logger logger = LoggerFactory.getLogger(HttpServer.class);

	private EventLoopGroup bossGroup;

	private EventLoopGroup workerGroup;

	private int port;

	@Override
	public void init() {
		ServerConfigs serverConfigs = SpringContext.getServerConfigs();
		this.port = serverConfigs.getHttpPort();
	}

	@Override
	public void start() throws Exception {
		bossGroup = new NioEventLoopGroup(1);
		workerGroup = new NioEventLoopGroup(1);
		ServerBootstrap b = new ServerBootstrap();
		b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
				.handler(new LoggingHandler(LogLevel.TRACE)).childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast("http-decorder", new HttpRequestDecoder());
						ch.pipeline().addLast("http-aggregator", new HttpObjectAggregator(512 * 1024));
						ch.pipeline().addLast("http-encoder", new HttpResponseEncoder());
						ch.pipeline().addLast("serve-handler", new HttpServerHandler());
					}
				});

		logger.info("http server start at [{}]", port);
		b.bind(new InetSocketAddress(port)).sync();
	}

	@Override
	public void shutDown() throws Exception {
		if (bossGroup != null) {
			bossGroup.shutdownGracefully();
		}
		if (workerGroup != null) {
			workerGroup.shutdownGracefully();
		}
	}

}
