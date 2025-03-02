package pers.kinson.im.infrastructure.security;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

/**
 * 使用RSA SHA256公钥解密的JWT令牌
 * 可验证收到的请求是否通过在Security工程中的私钥加密的。
 * Spring Security也提供了jks-uri的形式进行自动验证，为了便于对比，就没有节省这几行代码
 *
 **/
@Component
public class RSA256PublicJWTAccessToken extends JWTAccessToken {

    RSA256PublicJWTAccessToken(UserDetailsService userDetailsService) throws IOException {
        super(userDetailsService);
        Resource resource = new ClassPathResource("public.cert");
        String publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
        setVerifierKey(publicKey);
    }

}