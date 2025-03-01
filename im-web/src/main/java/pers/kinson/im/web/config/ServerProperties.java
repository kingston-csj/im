package pers.kinson.im.web.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "server")
@Component
@Getter
@Setter
public class ServerProperties {

    /**
     * 版本号
     */
    private String version;

    /**
     * emoji资源目录路径
     */
    private String emojiPath;

    /**
     * avatar资源目录路径
     */
    private String avatarPath;
}
