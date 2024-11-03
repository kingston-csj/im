package pers.kinson.im.chat.logic.file;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResUploadFile {

    private String id;

    private String url;

    private long time;

    private String name;

}