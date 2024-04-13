package pers.kinson.im.chat.net.codec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.net.message.AbstractPacket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<AbstractPacket> {

    private Logger logger = LoggerFactory.getLogger(PacketEncoder.class);

    @Override
    protected void encode(ChannelHandlerContext ctx, AbstractPacket msg, ByteBuf out)
            throws Exception {
        try {
            out.writeInt(msg.getPacketId());    //消息头
            //消息体
            byte[] msgBody = SpringContext.getMessageCodec().encode(msg);
            out.writeInt(msgBody.length);
            out.writeBytes(msgBody);
        } catch (Exception e) {
            logger.error("", e);
        }
    }


}
