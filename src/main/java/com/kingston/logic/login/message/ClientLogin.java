package com.kingston.logic.login.message;

import com.kingston.net.message.Packet;
import com.kingston.net.message.PacketType;

import io.netty.buffer.ByteBuf;

public class ClientLogin extends Packet{

	private String alertMsg;
	private byte isValid;
	
	@Override
	public void writePacketBody(ByteBuf buf) {
		writeUTF8(buf, alertMsg);
		buf.writeByte(isValid);
	}

	@Override
	public void readPacketBody(ByteBuf buf) {
		this.alertMsg = readUTF8(buf);
		this.isValid = buf.readByte();
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.ClientLogin;
	}

	@Override
	public void execPacket() {
		// TODO Auto-generated method stub
		
	}

	public String getAlertMsg() {
		return alertMsg;
	}

	public void setAlertMsg(String alertMsg) {
		this.alertMsg = alertMsg;
	}

	public byte getIsValid() {
		return isValid;
	}

	public void setIsValid(byte isValid) {
		this.isValid = isValid;
	}

}
