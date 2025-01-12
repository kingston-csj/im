package pers.kinson.im.chat.data.view;

import lombok.Data;

@Data
public class FriendView {

    private long userId;

    private String userName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 个性签名
     */
    private String signature;

    private byte sex;
    /**
     * 所属好友分组
     */
    private int groupId;
    /**
     * 分组备注
     */
    private String groupName;

    /**
     * 头像url
     */
    private String avatar;

}
