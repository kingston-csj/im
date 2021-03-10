package pers.kinson.im.chat.logic.login.message.res;

import pers.kinson.im.chat.base.Constants;
import pers.kinson.im.chat.net.IoSession;
import pers.kinson.im.chat.net.message.AbstractPacket;
import pers.kinson.im.chat.net.message.PacketType;

import io.netty.buffer.ByteBuf;

public class ResUserLogin extends AbstractPacket {

	private String alertMsg;
	private byte isValid;

	public static ResUserLogin valueOfFailed() {
		ResUserLogin response = new ResUserLogin();
		response.setIsValid(Constants.FAILED);

		return response;
	}

	@Override
	public void writeBody(ByteBuf buf) {
		writeUTF8(buf, alertMsg);
		buf.writeByte(isValid);
	}

	@Override
	public void readBody(ByteBuf buf) {
		this.alertMsg = readUTF8(buf);
		this.isValid = buf.readByte();
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.ResUserLogin;
	}

	@Override
	public void execPacket(IoSession session) {
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
