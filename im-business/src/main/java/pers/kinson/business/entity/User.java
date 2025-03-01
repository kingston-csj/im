package pers.kinson.business.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value = "user")
@Data
public class User {

    @TableId()
    private Long userId;
    /**
     * 性别
     */
    private byte sex;

    /**
     * 头像cdn地址
     */
    private String avatar;
    /**
     * 用户名字
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 个性签名
     */
    private String signature;

    /**
     * 点对点聊天seq
     */
    private long chatMaxSeq;

}
