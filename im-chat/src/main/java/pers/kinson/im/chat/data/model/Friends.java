package pers.kinson.im.chat.data.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName(value = "friends")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Friends {

    @TableId()
    private Long id;

    private Long userId;

    private Long friendId;

    private Long groupId;

    private String remark;

    private Date date;

}
