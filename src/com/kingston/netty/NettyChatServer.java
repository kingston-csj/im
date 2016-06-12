package com.kingston.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.kingston.net.codec.PacketDecoder;
import com.kingston.net.codec.PacketEncoder;

public class NettyChatServer {

	public void bind(int port) throws IOException{
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		System.err.println("服务端已启动，正在监听用户的请求......");
		try{
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup,workerGroup)
			.channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_BACKLOG, 1024)
			.childHandler(new ChildChannelHandler());
			
			ChannelFuture f = b.bind(new InetSocketAddress(port))
					.sync();
			f.channel().closeFuture().sync();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
	private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{

		@Override
		protected void initChannel(SocketChannel arg0) throws Exception {
			ChannelPipeline pipeline = arg0.pipeline();
			pipeline.addLast(new PacketDecoder(1024*1, 0,2,0,2));
			pipeline.addLast(new LengthFieldPrepender(2));
			pipeline.addLast(new PacketEncoder());
			pipeline.addLast("idleStateHandler", new IdleStateHandler(10, 0, 0)); 
			pipeline.addLast(new ChatServerHandler());
		}
	}
	
}
