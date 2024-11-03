package pers.kinson.im.chat.logic.file;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqUploadFile {

    public static final int TYPE_STORY = 1;
    public static final int TYPE_SPINE = 2;

    // 1为剧情导入 2为spine
    private Integer type;

    private String params;
}
