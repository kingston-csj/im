package pers.kinson.im.infrastructure.security;

import com.alibaba.nacos.shaded.com.google.common.collect.Maps;
import pers.kinson.im.infrastructure.config.OAuth2ClientProperty;
import pers.kinson.im.infrastructure.config.NacosCommonProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * OAuth2客户端类型定义
 * <p>
 * OAuth2支持四种授权模式，这里仅定义了密码模式（Resource Owner Password Credentials Grant）一种
 * OAuth2作为开放的（面向不同服务提供商）授权协议，要求用户提供明文用户名、密码的这种“密码模式”并不常用
 * 而这里可以采用是因为前端与后端服务是属于同一个服务提供者的，实质上不存在密码会不会被第三方保存的敏感问题
 * 如果永远只考虑单体架构、单一服务提供者，则并无引入OAuth的必要，Spring Security的表单认证就能很良好、便捷地解决认证和授权的问题
 * 这里使用密码模式来解决，是为了下一阶段演示微服务化后，服务之间鉴权作准备，以便后续扩展以及对比。
 **/
@Component
public class OAuthClientDetailsService implements ClientDetailsService {

    /**
     * 客户端列表
     * 此场景中微服务有浏览器前端，内部微服务两类客户端
     * 验证clientId与clientSecret @see BasicAuthenticationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)
     */
    private enum Client {

        Frontend("frontend", new String[]{GrantType.PASSWORD, GrantType.REFRESH_TOKEN}, new String[]{Scope.BROWSER}),

        Account("account", new String[]{GrantType.CLIENT_CREDENTIALS}, new String[]{Scope.SERVICE}),

        Security("security", new String[]{GrantType.CLIENT_CREDENTIALS}, new String[]{Scope.SERVICE}),

        Web("web", new String[]{GrantType.CLIENT_CREDENTIALS}, new String[]{Scope.SERVICE}),

        Chat("chat", new String[]{GrantType.CLIENT_CREDENTIALS}, new String[]{Scope.SERVICE}),


        ;

        final String name;

        /**
         * 授权类型
         * 前端API使用密码授权模式，微服务使用客户端授权模式
         */
        final String[] grantTypes;

        /**
         * 授权范围
         */
        final String[] scopes;

        Client(String name, String[] grantTypes, String[] scopes) {
            this.name = name;
            this.grantTypes = grantTypes;
            this.scopes = scopes;
        }
    }


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    NacosCommonProperties nacosCommonProperties;

    private ClientDetailsService clientDetailsService;

    /**
     * 构造密码授权模式
     * 授权Endpoint示例：
     * /oauth/token?grant_type=password & username=#USER# & password=#PWD# & client_id=im_frontend & client_secret=im_secret
     * 刷新令牌Endpoint示例：
     * /oauth/token?grant_type=refresh_token & refresh_token=#REFRESH_TOKEN# & client_id=im_frontend & client_secret=im_secret
     */
    @PostConstruct
    public void init() throws Exception {
        InMemoryClientDetailsServiceBuilder builder = new InMemoryClientDetailsServiceBuilder();
        for (Client client : Client.values()) {
            // 提供客户端ID和密钥，并指定该客户端支持密码授权、刷新令牌两种访问类型
            OAuth2ClientProperty prop = nacosCommonProperties.getClients().get(client.name);
            builder.withClient(prop.getClientId())
                    .secret(passwordEncoder.encode(prop.getClientSecret()))
                    .scopes(client.scopes)
                    .authorizedGrantTypes(client.grantTypes);
        }

        clientDetailsService = builder.build();
    }

    /**
     * 外部根据客户端ID查询验证方式
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return clientDetailsService.loadClientByClientId(clientId);
    }
}