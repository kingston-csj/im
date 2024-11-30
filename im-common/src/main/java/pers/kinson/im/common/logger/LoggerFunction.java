package pers.kinson.im.common.logger;

import org.slf4j.Logger;

public enum LoggerFunction {

    //文件上传
    UPLOAD,

    EMOIJ,

    ;


    public Logger getLogger() {
        return LoggerBuilder.getLogger(this.name());
    }


}
