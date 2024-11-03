package pers.kinson.im.oss;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileTypes {

    PICTURE("picture", "图片"),

    AVATAR("avatar", "头像"),

    VIDEO("video", "视频"),

    SOUND("sound", "声音"),

    FILE("file", "文件"),

    OTHER("other", "其他"),

    ;

    private final String path;

    private final String desc;
}
