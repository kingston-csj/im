package pers.kinson.im.oss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "s3")
@Component
@Data
public class OssConfig {

    /**
     * 这个值对于 amazon统一为 s3.amazonaws.com，
     * 对于MinIO，可自定义
     */
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;
    /**
     * cdn域名，只在客户端读取文件使用，服务端上传文件统一用endpoint
     * 对于一些文件内容可能会被修改，则不适用cdn。因为cdn在缓存期间是不会重新刷新资源的
     */
    private String cdn;
    /**
     * 使用minio，该值可以配置为amazon任何一个有效区域，唯一作用只是为了兼容amazonAPI
     */
    private String region;

}