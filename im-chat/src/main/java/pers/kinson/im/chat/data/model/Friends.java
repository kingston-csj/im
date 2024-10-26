package pers.kinson.im.chat.data.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import pers.kinson.im.chat.base.Constants;

@TableName(value = "friends")
@Data
public class Friends {

    @TableId()
    private Long id;

    private Long userId;

    private Long friendId;

    private Long groupId;

}
