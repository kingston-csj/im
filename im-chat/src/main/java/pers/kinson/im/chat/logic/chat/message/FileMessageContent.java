package pers.kinson.im.chat.logic.chat.message;

import lombok.Getter;
import lombok.Setter;
import pers.kinson.im.common.constants.ContentType;

@Getter
@Setter
public class FileMessageContent extends MediaMessageContent {

    private String name;

    private long size;

    public FileMessageContent() {
        setType(ContentType.file);
    }
}
