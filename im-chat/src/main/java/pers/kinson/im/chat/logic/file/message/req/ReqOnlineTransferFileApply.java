package pers.kinson.im.chat.logic.file.message.req;


import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.common.constants.CmdConst;

/**
 *
 */
@Data
@MessageMeta(cmd = CmdConst.ReqOnlineFileApply)
public class ReqOnlineTransferFileApply {

    private Long receiverId;

    private String fileName;

    private long fileSize;
    /**
     * 发送方文件本地路径
     */
    private String filePath;
}
