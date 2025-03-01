package pers.kinson.business.entity;

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
     * 处理状态 {@link pers.kinson.im.common.constants.CommonStatus#APPLY_STATUS_YES}
     */
    private byte status;

}
