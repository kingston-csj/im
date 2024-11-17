package pers.kinson.im.common.constants;

public interface CmdConst {

    /*
     * ------------------通用开始----------------------------
     */

    /**
     * 请求--链接心跳包
     */
    int ReqHeartBeat = 1_000;

    /**
     * 推送--新用户注册
     */
    int RespHeartBeat = 1_200;

    /**
     * 推送--通用响应
     */
    int ResCommon = 1_201;
    int ResRedPoint = 1_202;
    int RedPointVo = 1_203;



    /**
     * ·
     * 请求--新用户注册
     */
    int ReqUserRegister = 3_000;
    /**
     * 请求--请求--用户登陆
     */
    int ReqUserLogin = 3_001;

    /**
     * 推送--新用户注册
     */
    int ResUserRegister = 3_200;
    /**
     * 推送--用户登录
     */
    int ResUserLogin = 3_201;
    /**
     * 推送--玩家信息
     */
    int ResUserInfo = 3_202;





    /**
     * 请求--单聊
     */
    int ReqChatToUser = 4_000;
    /**
     * 请求--群聊
     */
    int ReqChatToGroup = 4_001;
    /**
     * 请求--创建讨论组
     */
    int ReqCreateDiscussion = 4_002;
    /**
     * 请求--加入讨论组
     */
    int ReqJoinDiscussion = 4_003;

    int ReqViewDiscussionList = 4_004;

    int ReqViewDiscussionMembers = 4_006;

    int ReqFetchNewMessage = 4_007;
    int ReqMarkNewMessage = 4_008;

    /**
     * 推送--单聊
     */
    int ResChatToUser = 4_200;
    /**
     * 推送--群聊
     */
    int ResChatToGroup = 4_201;

    int ResViewDiscussionList = 4_202;
    int DiscussionGroupVo = 4_203;
    int DiscussionGroupMemberVo = 4_204;
    int ResViewDiscussionMembers = 4_205;

    int ResNewMessageNotify = 4_206;
    int ChatMessageVo = 4_207;

    int ResNewMessage = 4_208;

    /*
     * ------------------好友开始----------------------------
     */

    /**
     * 请求－好友查询
     */
    int ReqSearchFriends = 5_000;

    int ReqApplyFriendList = 5_001;
    int ReqApplyFriend = 5_002;
    int ReqApplyResult = 5_003;
    /**
     * 推送--好友列表
     */
    int ResFriendList = 5_200;
    /**
     * 推送--好友登录
     */
    int ResFriendLogin = 5_201;

    /**
     * 推送--好友下线
     */
    int ResFriendLogout = 5_202;

    int ResSearchFriends = 5_203;

    int FriendVo = 5_204;
    int RecommendFriendVO = 5_205;
    int ResApplyFriend = 5_206;
    int ApplyFriendVo = 5_207;
    int ResApplyFriendList = 5_208;
    int ResApplyResult = 5_209;


    /*
     * ------------------好友结束----------------------------
     */


}
