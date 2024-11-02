package pers.kinson.im.common.constants;

public interface CommonStatus {

    /**
     * action成功的标识
     */
    byte SUCC = 1;
    /**
     * action失败的标识
     */
    byte FAILED = 0;

    /**
     * 性别男
     */
    byte SEX_OF_BOY = 0;
    /**
     * 性别女
     */
    byte SEX_OF_GIRL = 1;


    /**
     * 离线状态
     */
    byte OFFLINE_STATUS = 0;
    /**
     * 在线状态
     */
    byte ONLINE_STATUS = 1;

    /**
     * 邀请状态--同意
     */
    byte APPLY_STATUS_YES = 1;
    /**
     * 邀请状态--拒绝
     */
    byte APPLY_STATUS_NO = 2;


    byte FALSE = 0;

    byte TRUE = 1;

}
