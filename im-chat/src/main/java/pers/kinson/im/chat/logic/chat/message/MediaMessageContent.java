package pers.kinson.im.chat.logic.chat.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class MediaMessageContent extends MessageContent {

    /**
     * 文件云存储路径
     */
    private String url;

}
