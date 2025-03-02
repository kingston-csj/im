package pers.kinson.im.infrastructure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import pers.kinson.im.infrastructure.security.JWTAccessTokenService;

/**
 * 资源服务器配置
 * 配置资源服务访问权限，主流有两种方式：
 * 方式一：通过{@link HttpSecurity}的<code>antMatchers</code>方法集中配置
 * 方式二：启用全局方法级安全支持{@link EnableGlobalMethodSecurity} 通过方法注解来控制权限
 **/
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Autowired
    private JWTAccessTokenService tokenService;

    /**
     * 配置HTTP访问权限
     */
    public void configure(HttpSecurity http) throws Exception {
        // 基于JWT来绑定用户状态，服务端可以是无状态的
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //　关闭csrf
        http.csrf().disable();
        // 允许iframe嵌套
        http.headers().frameOptions().disable();
        // 访问安全规则：
        // 在HTTP过滤器层面，所有接口都允许未认证访问
        // 通过方法注解来控制权限
        http.authorizeRequests()
                .antMatchers("/emoji/list", "/avatar/list", "/system/clientApp") // 这里列出允许未认证访问的接口路径,临时处理
                .permitAll()
                .anyRequest().authenticated(); // 其余接口需要认证
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenServices(tokenService);
    }


}
