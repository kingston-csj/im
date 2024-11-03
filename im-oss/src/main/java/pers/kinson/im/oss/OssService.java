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

    @Autowired
    Environment environment;

    // 外网环境，需要https
    private boolean needHttps;

    @PostConstruct
    private void init() {
        String activatedProfile = environment.getProperty("spring.profiles.active");
        needHttps = "test".equalsIgnoreCase(activatedProfile) || "prev".equalsIgnoreCase(activatedProfile) || "prod".equalsIgnoreCase(activatedProfile);

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
     * @param filePath
     * @return
     */
    public String fullOssPath(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return "";
        }
        String url = "";
        if (StringUtils.isEmpty(ossConfig.getCdn())) {
            url =  ossConfig.getEndpoint() + "/" + ossConfig.getBucketName() + "/" + filePath;
        } else {
            // cdn路径包括桶地址了
            url =  ossConfig.getCdn() + "/" + filePath;
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
}
