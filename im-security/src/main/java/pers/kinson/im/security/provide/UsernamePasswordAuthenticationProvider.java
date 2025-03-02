package pers.kinson.im.security.provide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pers.kinson.im.infrastructure.security.AuthenticAccountDetailsService;

/**
 * 基于用户名、密码的身份认证器
 * 该身份认证器会被{@link AuthenticationManager}验证管理器调用
 * 验证管理器支持多种验证方式，这里基于用户名、密码的的身份认证是方式之一
 *
 */
@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AuthenticAccountDetailsService authenticAccountDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 认证处理
     * <p>
     * 根据用户名查询用户资料，对比资料中加密后的密码
     * 结果将返回一个Authentication的实现类（此处为UsernamePasswordAuthenticationToken）则代表认证成功
     * 返回null或者抛出AuthenticationException的子类异常则代表认证失败
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName().toLowerCase();
        String password = (String) authentication.getCredentials();
        // AuthenticationException的子类定义了多种认证失败的类型，这里仅处“理用户不存在”、“密码不正确”两种
        // 用户不存在的话会直接由loadUserByUsername()抛出异常
        UserDetails user = authenticAccountDetailsService.loadUserByUsername(username);
        if (!passwordEncoder.matches(password, user.getPassword())) throw new BadCredentialsException("用户或密码错误");
        // 认证通过，返回令牌
        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    }

    /**
     * 判断该验证器能处理哪些认证
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UsernamePasswordAuthenticationToken.class);
    }

}
