package pers.kinson.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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


    @TableId(type = IdType.AUTO)
    private Long id;

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
