package pers.kinson.im.chat.logic.chat.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageContent {


    /**
     * 冗余字段，减少参数传递
     */
    private byte type;

    private String content;

}
