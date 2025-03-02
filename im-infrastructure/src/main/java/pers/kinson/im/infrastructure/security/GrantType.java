package pers.kinson.im.infrastructure.security;

import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.implicit.ImplicitAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;

/**
 * OAuth2 授权类型
 *
 * @see ResourceOwnerPasswordAccessTokenProvider
 * @see ClientCredentialsAccessTokenProvider
 * @see ImplicitAccessTokenProvider
 * @see AuthorizationCodeAccessTokenProvider
 *
 **/
public interface GrantType {

    // 四种标准类型
    String PASSWORD = "password";
    String CLIENT_CREDENTIALS = "client_credentials";
    String IMPLICIT = "implicit";
    String AUTHORIZATIONCODE = "authorizationcode";

    // 用于刷新令牌
    String REFRESH_TOKEN = "refresh_token";

}
