package pers.kinson.im.account;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"pers.kinson.im.*"})
@EnableDiscoveryClient
@EnableCaching
public class AccountApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AccountApplication.class, args);
    }

}