package pers.kinson.im.chat.core;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.common.constants.CmdConst;

@Data
@MessageMeta(cmd = CmdConst.ResCommon)
public class CommonResponse {

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


    public static CommonResponse ok(String data) {
        CommonResponse r = new CommonResponse();
        r.setData(data);
        return r;
    }

    public static CommonResponse fail(int code) {
        CommonResponse r = new CommonResponse();
        r.setCode(code);
        return r;
    }

    public static CommonResponse ok() {
        return new CommonResponse();
    }

    public static CommonResponse valueOf(int code) {
        CommonResponse r = new CommonResponse();
        r.setCode(code);
        return r;
    }


}
