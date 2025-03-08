package pers.kinson.im.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"pers.kinson.im.*"})
public class SecurityApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SecurityApplication.class, args);
        ClientCredentialsResourceDetails bean = context.getBean(ClientCredentialsResourceDetails.class);


    }
}
