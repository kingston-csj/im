package pers.kinson.im.account.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.kinson.business.cache.UserProfile;
import pers.kinson.business.entity.User;
import pers.kinson.im.account.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class UserController {

    @Autowired
    AccountService accountService;

    @GetMapping("/findById/{userId}")
    public User findById(@PathVariable("userId") long userId) {
        return accountService.queryUser(userId);
    }

    @GetMapping("/findByName/{userId}")
    public User findByName(@PathVariable("username") String username) {
        return accountService.findAccountByUsername(username);
    }

    @GetMapping("/profile/{userId}")
    public UserProfile getProfile(@PathVariable("userId") long userId) {
        User user = accountService.queryUser(userId);
        if (user == null) {
            return null;
        }
        UserProfile profile = new UserProfile();
        profile.setName(user.getUserName());
        profile.setId(profile.getId());

        return profile;
    }


    @PatchMapping("/info/updateChatSeq/{userId}")
    public boolean updateChatSeq(@PathVariable("userId") long userId,
                                 @RequestParam("chatSeq") long chatSeq) {
        User user = accountService.queryUser(userId);
        user.setChatMaxSeq(chatSeq);
        accountService.saveUser(user);
        return true;
    }

}
