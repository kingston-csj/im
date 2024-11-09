package pers.kinson.im.chat.logic.discussion.message.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

    private Long userId;

    private String userName;
    private String content;

    private String date;
}
