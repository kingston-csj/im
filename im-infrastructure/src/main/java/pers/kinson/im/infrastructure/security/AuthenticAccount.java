package pers.kinson.im.infrastructure.security;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pers.kinson.business.entity.User;

import java.util.Collection;
import java.util.HashSet;

/**
 * 认证用户模型
 * 用户注册之后，包含其业务属性，如姓名、电话、地址，用于业务发生，存储于Account对象中
 * 也包含其用于认证的属性，譬如密码、角色、是否停用，存储于AuthenticAccount对象中
 *
 **/
public class AuthenticAccount extends User implements UserDetails {

    public AuthenticAccount(User origin) {
        super();
        BeanUtils.copyProperties(origin, this);
    }

    /**
     * 该用户拥有的授权，譬如读取权限、修改权限、增加权限等等
     */
    private Collection<GrantedAuthority> authorities = new HashSet<>();

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return getUserName();
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    /**
     * 账号是否过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 是否锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码是否过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否被锁定
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}

