package pers.kinson.im.oss;


import lombok.Builder;
import lombok.Data;

import java.io.InputStream;

@Data
@Builder
public class UploadFileVo {

    private InputStream inputStream;

    private String fileName;

    private String catalog;

    private String suffix;

    private String contentType;

    private long size;
}
