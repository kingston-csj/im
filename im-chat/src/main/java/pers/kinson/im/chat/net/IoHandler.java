package pers.kinson.im.chat.net;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.dispatch.CmdTask;
import pers.kinson.im.chat.net.message.AbstractPacket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class IoHandler extends ChannelInboundHandlerAdapter  {

	private final static Logger logger = LoggerFactory.getLogger(IoHandler.class);

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		if (!ChannelUtils.addChannelSession(ctx.channel(), new IoSession(ctx.channel()))) {
			ctx.channel().close();
			logger.error("Duplicate session,IP=[{}]",ChannelUtils.getIp(ctx.channel()));
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext context,Object msg)
			throws Exception {
		AbstractPacket message = (AbstractPacket)msg;
		logger.info("receive pact, content is {}", message.getClass().getSimpleName());

		final Channel channel = context.channel();
		IoSession session = ChannelUtils.getSessionBy(channel);

		// 不在io线程处理
//		PacketManager.INSTANCE.execPacket(session, message);
		// 扔到业务线程池处理
		CmdTask cmdTask = CmdTask.valueOf(session.getDispatchKey(), session, message);
		SpringContext.getMessageDispatcher().addMessageTask(cmdTask);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
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
				Channel channel = ctx.channel();
				SpringContext.getUserService().userLogout(channel, SessionCloseReason.OVER_TIME);
			}
		}
	}

}
