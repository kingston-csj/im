package com.kingston.net.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPOutputStream;

import com.kingston.net.Packet;

public class PacketEncoder extends MessageToByteEncoder<Packet> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out)
			throws Exception {
		out.writeShort(msg.getPacketType().getType());	//消息头
		//消息体
		if(msg.isUseCompression()){  //开启gzip压缩
			ByteBuf buf =  Unpooled.buffer();
			msg.writePacketBody(buf);
			byte[] content = new byte[buf.readableBytes()];
			buf.getBytes(0, content);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(bos);
			gzip.write(content);
			gzip.close();
			byte[] destBytes = bos.toByteArray();
			out.writeInt(destBytes.length);
			out.writeBytes(destBytes);
			bos.close();
		}else{
			msg.writePacketBody(out);
		}
	}

}
