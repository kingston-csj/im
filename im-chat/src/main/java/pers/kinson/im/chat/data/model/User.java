package pers.kinson.im.chat.data.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import pers.kinson.im.common.constants.CommonStatus;

@TableName(value = "user")
@Data
public class User {

    @TableId()
    private Long userId;
    /**
     * 性别{@link CommonStatus#SEX_OF_BOY}
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

}
