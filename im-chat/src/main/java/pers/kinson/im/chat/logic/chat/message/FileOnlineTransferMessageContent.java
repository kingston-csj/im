package pers.kinson.im.chat.logic.chat.message;

import lombok.Getter;
import lombok.Setter;
import pers.kinson.im.common.constants.ContentType;

@Getter
@Setter
public class FileOnlineTransferMessageContent extends MediaMessageContent {

    public static final byte STATUS_APPLY = 0;
    public static final byte STATUS_REJECT = 1;
    public static final byte STATUS_DOING  = 2;
    public static final byte STATUS_OK = 3;
    public static final byte STATUS_INTERRUPT = 4;

    private String requestId;

    private String name;

    private String fileUrl;

    private long size;

    private Long fromId;

    private Long toId;

    /**
     * 状态
     */
    private byte status;

    public FileOnlineTransferMessageContent() {
        setType(ContentType.onlineTransfer);
    }
}
