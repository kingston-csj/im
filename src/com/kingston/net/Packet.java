package com.kingston.net;
import io.netty.buffer.ByteBuf;

import java.io.UnsupportedEncodingException;
public abstract  class Packet {

	private int userId;
	
	public void writeToBuff(ByteBuf buf){
		buf.writeShort(getPacketType().getType());
		buf.writeInt(userId);
		writePacketMsg(buf);
	}
	
	abstract public void  writePacketMsg(ByteBuf buf);
	
	abstract public void  readFromBuff(ByteBuf buf);
	
	abstract public PacketType  getPacketType();
	
	abstract public void execPacket();
	
	protected  String readUTF8(ByteBuf buf){
		int strSize = buf.readInt();
		byte[] content = new byte[strSize];
		buf.readBytes(content);
		try {
			return new String(content,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
		
	}
	
	protected  void writeUTF8(ByteBuf buf,String msg){
		byte[] content ;
		try {
			content = msg.getBytes("UTF-8");
			buf.writeInt(content.length);
			buf.writeBytes(content);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
