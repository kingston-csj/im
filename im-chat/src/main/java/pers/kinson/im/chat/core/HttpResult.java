package pers.kinson.im.chat.core;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.chat.logic.CmdConst;

@Data
@MessageMeta(cmd =  CmdConst.ResCommon)
public class HttpResult {

    /**
     * 0表示成功，非0代表错误
     */
    private int code;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 真正的消息，格式由业务自行定义
     */
    private String data;



    public static HttpResult ok(String data) {
        HttpResult r = new HttpResult();
        r.setData(data);
        return r;
    }

    public static HttpResult ok() {
        return new HttpResult();
    }

    public static HttpResult valueOf(int code) {
        HttpResult r = new HttpResult();
        r.setCode(code);
        return r;
    }


}
