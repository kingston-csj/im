package pers.kinson.im.chat.logic.chat.message.req;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.common.constants.CmdConst;

@Data
@MessageMeta(cmd = CmdConst.ReqChatToChannel)
public class ReqChatToChannel {

    /**
     * 频道
     */
    private byte channel;

    private long toUserId;

    /**
     * 消息类型{@link pers.kinson.im.common.constants.ContentType}
     */
    private byte contentType;

    private String content;

}
