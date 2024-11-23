package pers.kinson.im.chat.logic.chat.message;

import lombok.Getter;
import lombok.Setter;
import pers.kinson.im.common.constants.ContentType;

@Getter
@Setter
public class ImageMessageContent extends MediaMessageContent {

    public ImageMessageContent() {
        setType(ContentType.image);
    }
}
