package pers.kinson.im.chat.logic.file;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResUploadFile {

    private String id;

    /**
     * 真正的cdn地址
     */
    private String cdnUrl;

    /**
     * 上传预签名地址
     */
    private String presignedUrl;

    private long time;

    private String name;

}