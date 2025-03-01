package pers.kinson.im.common.constants;

public interface I18nConstants {

    int COMMON_NOT_FOUND = 1001;  //资源无法找到
    int COMMON_INTERNAL_ERROR = 1002;  //服务器异常
    int COMMON_ILLEGAL_PARAMS = 1003;  //非法参数
    int COMMON_RESOURCE_ERROR = 1004;  //资源已损坏
    int COMMON_VERIFY_FAILED = 1005;  //验证不通过
    int COMMON_UNAUTHORIZED = 1006;   //未授权
    int COMMON_TOKEN_EXPIRED = 1007;   //token失效
    int COMMON_DATA_ERROR = 1008;     //数据格式异常（客户端数据不符合约定格式）


    int FRIEND_APPLY_ALREADY = 2001; // 已申请
    int FRIEND_ALREADY = 2002; // 已经是基友啦
    int CHAT_TRANSFER_TARGET_OFFLINE = 2003; // 对方离线，无法传输
    int USER_ID_EXISTED = 2004; // 对方离线，无法传输
}