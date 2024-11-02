package pers.kinson.im.chat.logic.friend.message.vo;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.common.constants.CmdConst;
import pers.kinson.im.common.constants.CommonStatus;

@Data
@MessageMeta(cmd = CmdConst.ApplyFriendVo)
public class FriendApplyVo {

	private long id;

	private long fromId;
	private long toId;
	/** 昵称 */
	private String fromName;
	private String toName;
	/** 备注 */
	private String remark;
	/**
	 * 处理状态 {@link CommonStatus#APPLY_STATUS_YES}
	 */
	private byte status;

}
