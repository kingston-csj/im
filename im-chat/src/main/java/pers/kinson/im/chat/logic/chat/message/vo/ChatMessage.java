package pers.kinson.im.chat.logic.chat.message.vo;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pers.kinson.im.common.constants.CmdConst;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@MessageMeta(cmd = CmdConst.ChatMessageVo)
public class ChatMessage {

    private Long userId;

    private String userName;
    private String content;

    private String date;
}
