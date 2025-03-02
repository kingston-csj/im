package pers.kinson.im.infrastructure.security;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.kinson.business.cache.UserProfile;
import pers.kinson.business.entity.User;
import pers.kinson.im.infrastructure.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "account", configuration = {FeignConfiguration.class})
public interface AccountServiceClient {

    @GetMapping("/accounts/findByName/{username}")
    User findByUsername(@PathVariable("username") String username);

    @GetMapping("/accounts/findById/{userId}")
    User findById(@PathVariable("userId") long userId);

    @GetMapping("/accounts/profile/{userId}")
    UserProfile queryUserProfile(@PathVariable("userId") long userId);

    @PatchMapping("/accounts/info/updateChatSeq/{userId}")
    boolean updateChatSeq(@PathVariable("userId") long userId,
                          @RequestParam("chatSeq") long chatSeq);
}
