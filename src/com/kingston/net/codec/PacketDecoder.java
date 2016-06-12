package com.kingston.net.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import com.kingston.net.Packet;
import com.kingston.net.PacketManager;

public class PacketDecoder extends LengthFieldBasedFrameDecoder{

	public PacketDecoder(int maxFrameLength,
			int lengthFieldOffset, int lengthFieldLength,
			int lengthAdjustment, int initialBytesToStrip
			) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength,
				lengthAdjustment, initialBytesToStrip);
	}

	@Override
	public Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
		ByteBuf frame = (ByteBuf)(super.decode(ctx, in));
		if(frame.readableBytes() <= 0) return null ;
		short packetType = frame.readShort();
		Packet packet = PacketManager.createNewPacket(packetType);
		packet.setUserId(frame.readInt());
		packet.readFromBuff(frame);

		return packet;
	}

}
