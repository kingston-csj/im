package pers.kinson.im.common.constants;

public interface CmdConst {

    /*
     * ------------------通用开始----------------------------
     */

    /**
     * 请求--链接心跳包
     */
    int ReqHeartBeat = 1000;

    /**
     * 推送--新用户注册
     */
    int RespHeartBeat = 1200;

    /**
     * 推送--通用响应
     */
    int ResCommon = 1201;
    int ResRedPoint = 1202;
    int RedPointVo = 1203;



    /**
     * ·
     * 请求--新用户注册
     */
    int ReqUserRegister = 3000;
    /**
     * 请求--请求--用户登陆
     */
    int ReqUserLogin = 3001;

    /**
     * 推送--新用户注册
     */
    int ResUserRegister = 3200;
    /**
     * 推送--用户登录
     */
    int ResUserLogin = 3201;
    /**
     * 推送--玩家信息
     */
    int ResUserInfo = 3202;





    /**
     * 请求--单聊
     */
    int ReqChatToUser = 4000;
    /**
     * 请求--群聊
     */
    int ReqChatToChannel = 4001;
    /**
     * 请求--创建讨论组
     */
    int ReqCreateDiscussion = 4002;
    /**
     * 请求--加入讨论组
     */
    int ReqJoinDiscussion = 4003;

    int ReqViewDiscussionList = 4004;

    int ReqViewDiscussionMembers = 4006;

    int ReqFetchNewMessage = 4007;
    int ReqMarkNewMessage = 4008;

    /**
     * 在线文件传输邀请
     */
    int ReqOnlineFileApply = 4009;
    int ReqOnlineFileAnswer = 4010;
    int ReqOnlineFileFinish = 4011;


    /**
     * 推送--群聊
     */
    int ResChatToGroup = 4201;

    int ResViewDiscussionList = 4202;
    int DiscussionGroupVo = 4203;
    int DiscussionGroupMemberVo = 4204;
    int ResViewDiscussionMembers = 4205;

    int ResNewMessageNotify = 4206;
    int ChatMessageVo = 4207;

    int ResNewMessage = 4208;

    int ResOnlineFileApply = 4209;
    int ResModifyMessage = 4210;
    int PushBeginOnlineFileTransfer = 4211;

    /*
     * ------------------好友开始----------------------------
     */

    /**
     * 请求－好友查询
     */
    int ReqSearchFriends = 5000;

    int ReqApplyFriendList = 5001;
    int ReqApplyFriend = 5002;
    int ReqApplyResult = 5003;
    int ReqFriendOnlineStatus = 5004;

    /**
     * 推送--好友列表
     */
    int ResFriendList = 5200;
    int ResSearchFriends = 5203;

    int FriendVo = 5204;
    int RecommendFriendVO = 5205;
    int ResApplyFriend = 5206;
    int ApplyFriendVo = 5207;
    int ResApplyFriendList = 5208;
    int ResApplyResult = 5209;
    int ResFriendOnlineStatus = 5210;


    /*
     * ------------------好友结束----------------------------
     */


}
