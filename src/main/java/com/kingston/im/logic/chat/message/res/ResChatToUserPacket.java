package com.kingston.im.logic.chat.message.res;

import com.kingston.im.net.IoSession;
import com.kingston.im.net.message.AbstractPacket;
import com.kingston.im.net.message.PacketType;

import io.netty.buffer.ByteBuf;

public class ResChatToUserPacket extends AbstractPacket {

	private long fromUserId;

	private String content;

	@Override
	public void writeBody(ByteBuf buf) {
		buf.writeLong(fromUserId);
		writeUTF8(buf, content);
	}

	@Override
	public void readBody(ByteBuf buf) {
		this.fromUserId = buf.readLong();
		this.content = readUTF8(buf);
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.ResChatToUser;
	}

	@Override
	public void execPacket(IoSession session) {
		// TODO Auto-generated method stub
	}

	public long getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(long fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ResChatToUserPacket [fromUserId=" + fromUserId + ", content=" + content + "]";
	}

}
