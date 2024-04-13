package pers.kinson.im.chat.logic.user.message.res;

import lombok.Data;
import pers.kinson.im.chat.logic.CmdConst;
import pers.kinson.im.chat.net.message.AbstractPacket;

@Data
public class ResUserInfo extends AbstractPacket {

	private long userId;
	/** 账号昵称 */
	private String userName;
	/** 性别 */
	private byte sex;
	/** 个性签名　*/
	private String signature;

	@Override
	public int getPacketId() {
		return CmdConst.ResUserInfo;
	}

	@Override
	public String toString() {
		return "ResUserInfoMessage [userId=" + userId + ", userName=" + userName + ", signature=" + signature + ", sex="
				+ sex + "]";
	}

}
