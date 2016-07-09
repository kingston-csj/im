package com.kingston.net;

import io.netty.buffer.ByteBuf;

import java.io.UnsupportedEncodingException;

public abstract  class Packet {

	abstract public void writePacketBody(ByteBuf buf);

	abstract public void readPacketBody(ByteBuf buf);

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

	/**
	 *  是否开启gzip压缩(默认关闭)
	 *  消息体数据大的时候才开启，非常小的包压缩后体积反而变大，而且耗时
	 */
	public boolean isUseCompression() {
		return false;
	}


}
