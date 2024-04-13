package pers.kinson.im.chat.data.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import pers.kinson.im.chat.base.Constants;

@TableName(value = "user")
@Data
public class User {

    @TableId()
    private Long userId;
    /**
     * 性别{@link Constants#SEX_OF_BOY}
     */
    private byte sex;
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
