package pers.kinson.im.chat.data.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 好友申请
 */
@TableName(value = "ossResource")
@Data
public class OssResource {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String type;

    /**
     * oss内部路径
     */
    private String url;

    /**
     * 标签(别名)
     */
    private String label;

    /**
     * 原始文件名称
     */
    private String originalName;

    private String md5;

    private Date createdDate;

}
