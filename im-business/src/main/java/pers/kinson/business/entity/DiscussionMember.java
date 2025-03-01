package pers.kinson.business.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 好友申请
 */
@TableName(value = "discussionmember")
@Data
public class DiscussionMember {

    @TableId()
    private Long id;

    private Long userId;

    private Long discussionId;

    private long maxSeq;

    /**
     * 群里昵称
     */
    private String nickName;

    private Date joinDate;

    /**
     * 职位
     */
    private byte position;
}
