package pers.kinson.im.infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 读取shared-config的代码 @see NacosPropertySourceLocator
 */
@ConfigurationProperties(prefix = "security.oauth2")
@Component
public class NacosCommonProperties {

    @Getter
    @Setter
    private Map<String, OAuth2ClientProperty> clients;

}

