package pers.kinson.im.common.logger;

import org.slf4j.Logger;

public enum LoggerFunction {

    //游戏发布
    PUBLISH,
    //文件上传
    UPLOAD,
    // url请求
    REQUEST,
    // 平台操作
    PLATFORM,
    // 后台命令
    ADMIN,
    // 素材
    MATERIAL,
    // 玩家操作
    PLAYER,
    // 模板
    TEMPLATE,
    // 版本
    VERSION,
    // 调试数据
    DEBUG,
    // 业务bean验证
    VALIDATE,
    // 服务器重要数据
    APPLICATION,
    // 监控
    MONITOR,
    // 调用第三方服务
    THIRD_PARTY,



    ;


    public Logger getLogger() {
        return LoggerBuilder.getLogger(this.name());
    }


}
