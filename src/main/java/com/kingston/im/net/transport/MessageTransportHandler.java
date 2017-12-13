package com.kingston.im.net.transport;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kingston.im.ServerConfigs;
import com.kingston.im.base.ServerManager;
import com.kingston.im.base.SpringContext;
import com.kingston.im.logic.login.LoginService;
import com.kingston.im.logic.login.message.req.ReqUserLoginPacket;
import com.kingston.im.logic.login.message.res.ResHeartBeatPacket;
import com.kingston.im.logic.user.UserService;
import com.kingston.im.logic.user.message.req.ReqUserRegisterPacket;
import com.kingston.im.net.ChannelUtils;
import com.kingston.im.net.IoSession;
import com.kingston.im.net.SessionCloseReason;
import com.kingston.im.net.message.AbstractPacket;
import com.kingston.im.net.message.PacketManager;
import com.kingston.im.net.message.PacketType;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class MessageTransportHandler extends ChannelHandlerAdapter{

	private final static Logger logger = LoggerFactory.getLogger(MessageTransportHandler.class);

	//客户端超时次数
	private Map<ChannelHandlerContext,Integer> clientOvertimeMap = new ConcurrentHashMap<>();

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		if (!ChannelUtils.addChannelSession(ctx.channel(), new IoSession(ctx.channel()))) {
			ctx.channel().close();
			logger.error("Duplicate session,IP=[{}]",ChannelUtils.getIp(ctx.channel()));
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext context,Object msg)
			throws Exception{
		AbstractPacket  packet = (AbstractPacket)msg;
		logger.info("receive pact, content is {}", packet.getClass().getSimpleName());

		final Channel channel = context.channel();
		IoSession session = ChannelUtils.getSessionBy(channel);

		if (packet.getPacketType() == PacketType.ReqUserRegister) {
			ReqUserRegisterPacket registerPact = (ReqUserRegisterPacket)packet;
			UserService userService = SpringContext.getUserService();
			userService.registerNewAccount(channel, registerPact.getSex(), registerPact.getNickName(), registerPact.getPassword());
			return;
		}else if (packet.getPacketType() == PacketType.ReqUserLogin) {
			ReqUserLoginPacket loginPact = (ReqUserLoginPacket)packet;
			LoginService loginMgr = SpringContext.getBean(LoginService.class);
			loginMgr.validateLogin(channel,loginPact.getUserId(), loginPact.getUserPwd());
			return ;
		}

		if(validateSession(packet)){
			PacketManager.INSTANCE.execPacket(session, packet);
		}

		clientOvertimeMap.remove(context);//只要接受到数据包，则清空超时次数

	}

	private  boolean validateSession(AbstractPacket loginPact){
		return true;
	}

	@Override
	public void close(ChannelHandlerContext ctx,ChannelPromise promise){
		logger.error("TCP closed...");
		ctx.close(promise);
	}


	@Override
	public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		ctx.disconnect(promise);
		logger.error("客户端关闭");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.error("业务逻辑出错", cause);
		cause.printStackTrace();
		Channel channel = ctx.channel();
		if(cause instanceof IOException && channel.isActive()){
			logger.error("simpleclient"+channel.remoteAddress()+"异常");
			SpringContext.getUserService().userLogout(channel, SessionCloseReason.NORMAL);
			ctx.close();
		}
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {
		//心跳包检测读超时
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent e = (IdleStateEvent) evt;
			if (e.state() == IdleState.ALL_IDLE) {
				logger.info("客户端读超时");
//				int overtimeTimes = clientOvertimeMap.getOrDefault(ctx, 0);
//				if(overtimeTimes < ServerConfigs.MAX_RECONNECT_TIMES){
//					ServerManager.INSTANCE.sendPacketTo(ctx.channel(), new ResHeartBeatPacket());
//					addUserOvertime(ctx);
//				}else{
//					ServerManager.INSTANCE.ungisterUserContext(ctx.channel());
//				}
				Channel channel = ctx.channel();
				SpringContext.getUserService().userLogout(channel, SessionCloseReason.OVER_TIME);

			}
		}
	}

	private void addUserOvertime(ChannelHandlerContext ctx){
		int oldTimes = 0;
		if(clientOvertimeMap.containsKey(ctx)){
			oldTimes = clientOvertimeMap.get(ctx);
		}
		clientOvertimeMap.put(ctx, (int)(oldTimes+1));
	}
}
