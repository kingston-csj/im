package pers.kinson.im.oss;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class OssService {

    @Autowired
    OssConfig ossConfig;

    private Map<String, String> allowFileTypes = new HashMap<>();

    private Set<String> alllowPictureTypes = new HashSet<>();

    private Map<String, String> contentTypes = new HashMap<>();

    @Autowired
    Environment environment;

    // 外网环境，需要https
    private boolean needHttps;

    @PostConstruct
    private void init() {
        String activatedProfile = environment.getProperty("spring.profiles.active");
        needHttps = "test".equalsIgnoreCase(activatedProfile) || "prod".equalsIgnoreCase(activatedProfile);

        allowFileTypes.put("jpg", FileTypes.PICTURE.getPath());
        allowFileTypes.put("png", FileTypes.PICTURE.getPath());
        allowFileTypes.put("jpeg", FileTypes.PICTURE.getPath());
        allowFileTypes.put("gif", FileTypes.PICTURE.getPath());
        allowFileTypes.put("mp3", FileTypes.SOUND.getPath());
        allowFileTypes.put("mp4", FileTypes.VIDEO.getPath());

        alllowPictureTypes.add("jpg");
        alllowPictureTypes.add("png");
        alllowPictureTypes.add("jpeg");
        alllowPictureTypes.add("gif");

        contentTypes.put("jpg", "image/jpeg");
        contentTypes.put("jpeg", "image/jpeg");
        contentTypes.put("png", "image/png");
        contentTypes.put("gif", "image/gif");
        contentTypes.put("mp3", "audio/mpeg");
        contentTypes.put("mp4", "video/mp4");
        contentTypes.put("ttf", "font/ttf");
        contentTypes.put("json", "application/json");
        contentTypes.put("txt", "text/plain");
        contentTypes.put("atlas", "text/plain");
    }

    public boolean isPicture(String suffix) {
        return alllowPictureTypes.contains(suffix);
    }

    /**
     * 图片全路径，包括ossUrl+图片本身路径
     */
    public String fullOssPath(FileTypes fileType, String filePath) {
        String fullName = fileType.getPath() + "/" + filePath;
        return fullOssPath(fullName);
    }

    /**
     * 图片在oss内部路径
     *
     * @param fileName
     * @return
     */
    public String ossImagePath(String fileName) {
        String stuff = fileName.substring(fileName.lastIndexOf(".") + 1);
        return getPathOf(stuff) + "/" + fileName;
    }

    /**
     * 完整路径分成三个部分：
     * s3本身http地址（桶地址）+程序内部二级目录+文件名
     *
     * @param filePath
     * @return
     */
    public String fullOssPath(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return "";
        }
        String url = "";
        if (StringUtils.isEmpty(ossConfig.getCdn())) {
            url = ossConfig.getEndpoint() + "/" + ossConfig.getBucketName() + "/" + filePath;
        } else {
            // cdn路径包括桶地址了
            url = ossConfig.getCdn() + "/" + filePath;
        }
        if (needHttps) {
            return url;
        }
        return url.replace("https", "http");
    }

    public String getPathOf(String type) {
        return allowFileTypes.getOrDefault(type, FileTypes.OTHER.getPath());
    }

    public boolean allowFileType(String stuff) {
        return allowFileTypes.containsKey(stuff);
    }

    public String getContentType(String type) {
        return contentTypes.getOrDefault(type, "application/octet-stream");
    }
}
