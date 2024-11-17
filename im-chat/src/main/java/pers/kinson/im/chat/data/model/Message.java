package pers.kinson.im.chat.data.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName(value = "message")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {


    public static byte TYPE_NORMAL  = 0;


    @TableId()
    private Long id;

    private Long seq;

    private String content;

    private byte channel;

    private byte type;

    /**
     * 频道不同，接收者引用不同
     */
    private Long receiver;

    private Long sender;

    private Date date;

}
