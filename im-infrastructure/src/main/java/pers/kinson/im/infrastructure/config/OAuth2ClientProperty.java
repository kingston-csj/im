package pers.kinson.im.infrastructure.config;

import lombok.Data;

@Data
public class OAuth2ClientProperty {

    /**
     * 客户端ID, 可以公开
     */
    private String clientId;

    /**
     * 客户端密钥, 应当保密
     */
    private String clientSecret;

}
