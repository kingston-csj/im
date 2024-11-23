package pers.kinson.im.chat.logic.chat;

import jakarta.annotation.PostConstruct;
import jforgame.commons.JsonUtil;
import org.springframework.stereotype.Service;
import pers.kinson.im.chat.logic.chat.message.MediaMessageContent;
import pers.kinson.im.chat.logic.chat.message.MessageContent;
import pers.kinson.im.common.constants.ContentType;

import java.util.HashMap;
import java.util.Map;

@Service
public class MessageContentFactory {

    Map<Byte, Class<? extends MessageContent>> mapper = new HashMap<>();

    @PostConstruct
    public void init() {
        mapper.put(ContentType.text, MessageContent.class);
        mapper.put(ContentType.image, MediaMessageContent.class);
    }

    public MessageContent parse(byte type, String json) {
        return JsonUtil.string2Object(json, mapper.get(type));
    }
}
