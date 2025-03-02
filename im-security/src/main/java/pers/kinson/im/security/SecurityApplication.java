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

        String password = "$apr1$1001$RY0r9WJwPR8hE/ylZsg6l.";

        // 成本因子为10
        BCryptPasswordEncoder encoder10 = new BCryptPasswordEncoder(10);
        long startTime10 = System.currentTimeMillis();

            String hash10 = encoder10.encode(password);
        System.out.println(hash10);
        long endTime10 = System.currentTimeMillis();
        System.out.println("Cost factor 10, Time taken: " + (endTime10 - startTime10) + " ms");
    }
}
