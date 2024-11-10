package pers.kinson.im.chat.data.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "message")
public class Message {

    @TableId()
    private Long id;

    private Long seq;

    private String content;

    private byte channel;

    /**
     * 频道不同，接收者引用不同
     */
    private String receiver;

    private Long sender;

    private Date date;

}
