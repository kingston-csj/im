package com.kingston.transport;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.kingston.net.codec.PacketDecoder;
import com.kingston.net.codec.PacketEncoder;

public class ChatServer {

	public void bind(int port) throws IOException {
		//避免使用默认线程数参数
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors());
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
		            pipeline.addLast(new PacketDecoder(1024*4,0,4,0,4));
		            pipeline.addLast(new LengthFieldPrepender(4));
		            pipeline.addLast(new PacketEncoder());
		            //当有操作操作超出指定空闲秒数时，便会触发UserEventTriggered事件
		            pipeline.addLast("idleStateHandler", new IdleStateHandler(10, 0, 0));
		            pipeline.addLast(new MessageTransportHandler());
		}
	}
	
}
