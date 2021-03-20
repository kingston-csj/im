package pers.kinson.im.chat.logic.login.message.res;

import io.netty.buffer.ByteBuf;
import pers.kinson.im.chat.base.Constants;
import pers.kinson.im.chat.logic.CmdConst;
import pers.kinson.im.chat.net.message.AbstractPacket;

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
	public int getPacketId() {
		return CmdConst.ResUserLogin;
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
