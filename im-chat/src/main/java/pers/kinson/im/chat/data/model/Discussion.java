package pers.kinson.im.chat.data.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 好友申请
 */
@TableName(value = "discussion")
@Data
public class Discussion {

    @TableId()
    private Long id;

    private String name;

    private Long creatorId;

    private Date createdDate;

    private Long maxSeq;

    private String avatar;

}
