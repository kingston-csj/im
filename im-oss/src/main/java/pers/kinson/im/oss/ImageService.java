package pers.kinson.im.oss;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class ImageService {

    @Autowired
    OssConfig ossConfig;

    private Map<String, String> allowFileTypes = new HashMap<>();


    @PostConstruct
    private void init() {
        allowFileTypes.put("jpg", FileTypes.PICTURE.getPath());
        allowFileTypes.put("png", FileTypes.PICTURE.getPath());
        allowFileTypes.put("jpeg", FileTypes.PICTURE.getPath());
        allowFileTypes.put("gif", FileTypes.PICTURE.getPath());
        allowFileTypes.put("mp3", FileTypes.SOUND.getPath());
        allowFileTypes.put("mp4", FileTypes.VIDEO.getPath());
    }

    /**
     * 完整cdn路径，分成三个部分：
     * s3本身http地址（桶地址）+程序内部二级目录+文件名
     *
     * @param filePath
     * @return
     */
    public String fullCdnPath(String filePath) {
        if (StringUtils.isEmpty(ossConfig.getCdn())) {
            return fullRawOssPath(filePath);
        } else {
            // 分类
            String category = suffixType(filePath);
            // cdn路径包括桶地址了
            return ossConfig.getCdn() + "/" + category + "/" + filePath;
        }
    }

    /**
     * 完整oss路径，分成三个部分：
     * <br>不</br>使用cdn，用于同一个链接内容实时变化的资源
     */
    public String fullRawOssPath(String filePath) {
        // 分类
        String category = suffixType(filePath);
        return ossConfig.getEndpoint() + "/" + category + "/" + ossConfig.getBucketName() + "/" + filePath;
    }

    /**
     * 返回一个文件的类型后缀(统一返回小写)
     *
     * @param fileName
     * @return
     */
    public static String suffixType(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }

    public String getPathOf(String type) {
        type = type.toLowerCase();
        return allowFileTypes.getOrDefault(type, FileTypes.OTHER.getPath());
    }

}