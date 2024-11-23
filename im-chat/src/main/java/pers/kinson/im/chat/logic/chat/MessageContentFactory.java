package pers.kinson.im.chat.logic.chat;

import jakarta.annotation.PostConstruct;
import jforgame.commons.JsonUtil;
import org.springframework.stereotype.Service;
import pers.kinson.im.chat.logic.chat.message.FileMessageContent;
import pers.kinson.im.chat.logic.chat.message.ImageMessageContent;
import pers.kinson.im.chat.logic.chat.message.MediaMessageContent;
import pers.kinson.im.chat.logic.chat.message.MessageContent;
import pers.kinson.im.chat.logic.chat.message.TextMessageContent;
import pers.kinson.im.common.constants.ContentType;

import java.util.HashMap;
import java.util.Map;

@Service
public class MessageContentFactory {

    Map<Byte, Class<? extends MessageContent>> mapper = new HashMap<>();

    @PostConstruct
    public void init() {
        mapper.put(ContentType.text, TextMessageContent.class);
        mapper.put(ContentType.image, ImageMessageContent.class);
        mapper.put(ContentType.file, FileMessageContent.class);
    }

    public MessageContent parse(byte type, String json) {
        return JsonUtil.string2Object(json, mapper.get(type));
    }
}
