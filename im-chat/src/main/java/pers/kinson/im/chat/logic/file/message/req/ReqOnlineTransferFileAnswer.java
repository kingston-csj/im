package pers.kinson.im.chat.logic.file.message.req;


import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.common.constants.CmdConst;

@Data
@MessageMeta(cmd = CmdConst.ReqOnlineFileAnswer)
public class ReqOnlineTransferFileAnswer {


    private Long messageId;

    /**
     * 1同意0拒绝
     */
    private byte status;

    /**
     * 点对点http传输通信
     */
    private String host;

    /**
     * 登录密钥
     */
    private String secretKey;


    private String fileName;

    /**
     * 允许的文件url
     */
    private String fileUrl;

}
