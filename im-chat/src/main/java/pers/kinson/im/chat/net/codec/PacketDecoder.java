package pers.kinson.im.chat.net.codec;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;

import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.net.message.AbstractPacket;
import pers.kinson.im.chat.net.message.MessageRouter;

public class PacketDecoder extends LengthFieldBasedFrameDecoder{

	public PacketDecoder(int maxFrameLength, int lengthFieldOffset,
						 int lengthFieldLength, int lengthAdjustment,
						 int initialBytesToStrip) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment,
				initialBytesToStrip);
	}

	public Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
		ByteBuf frame = (ByteBuf) super.decode(ctx, in);
		if (frame == null) {
			return null;
		}
		if(frame.readableBytes() <= 0) return null;

		int packetType = frame.readInt();
		AbstractPacket packet = MessageRouter.INSTANCE.createNewPacket(packetType);
		int bodyLength = frame.readInt();	//先读压缩数据的长度
		byte[] sourceBytes  = new byte[bodyLength];
		frame.readBytes(sourceBytes);

		return SpringContext.getMessageCodec().decode(packet.getClass(), sourceBytes);
	}


}
