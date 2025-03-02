package pers.kinson.im.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 认证用户信息查询服务
 */
@Component
public class AuthenticAccountDetailsService implements UserDetailsService {

    @Autowired
    private AuthenticAccountRepository accountRepository;

    /**
     * 根据用户名查询用户角色、权限等信息
     * 如果无法打到对应用户，或者权限不足，直接抛出{@link UsernameNotFoundException}
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(accountRepository.findByUserId(username)).orElseThrow(() -> new UsernameNotFoundException("未找到该用户:" + username));
    }

    public String getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }

    public Long getCurrentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AuthenticAccount) {
            return ((AuthenticAccount) principal).getUserId();
        }
        return 0L;
    }
}
