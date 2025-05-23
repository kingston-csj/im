package pers.kinson.im.chat.logic.friend.message.vo;

import lombok.Data;

@Data
public class FriendItemVo {

    private long userId;
    /**
     * 在线状态
     */
    private byte online;
    /**
     * 昵称
     */
    private String userName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 个性签名
     */
    private String signature;
    /**
     * 　性别
     */
    private byte sex;
    /**
     * 所属好友分组
     */
    private int group;
    /**
     * 分组备注
     */
    private String groupName;

    private String headUrl;

}
