package pers.kinson.im.chat.logic.user.message.res;

import lombok.Data;
import pers.kinson.im.chat.logic.CmdConst;
import pers.kinson.im.chat.net.message.AbstractPacket;

@Data

public class ResUserRegister extends AbstractPacket {
	
	private byte resultCode;
	
	private String message;
	
	@Override
	public int getPacketId() {
		return CmdConst.ResUserRegister;
	}

	@Override
	public String toString() {
		return "ResUserRegisterPacket [resultCode=" + resultCode + ", message=" + message + "]";
	}
	
}
