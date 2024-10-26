package pers.kinson.im.chat.data.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

/**
 * 好友申请
 */
@TableName(value = "friendapply")
@Data
public class FriendApply {

    /**
     * 流水号，无实际意义
     */
    @TableId()
    private Long id;

    private Long fromId;

    private Long toId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 申请日期
     */
    private Date date;

    /**
     * 1拒绝，2通过
     */
    private byte status;

}
