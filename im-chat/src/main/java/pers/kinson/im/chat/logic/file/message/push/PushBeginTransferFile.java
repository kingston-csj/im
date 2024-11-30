package pers.kinson.im.chat.logic.file.message.push;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.common.constants.CmdConst;

@Data
@MessageMeta(cmd = CmdConst.PushBeginOnlineFileTransfer)
public class PushBeginTransferFile {

    private String requestId;

    /**
     * 接收方http传输通信
     */
    private String host;

    /**
     * 登录密钥
     */
    private String secretKey;


    private String fileName;


    private String fileUrl;
}
