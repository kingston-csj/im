package pers.kinson.im.web.logic.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.kinson.business.entity.User;
import pers.kinson.im.common.HttpResult;
import pers.kinson.im.common.constants.I18nConstants;
import pers.kinson.im.web.logic.user.message.req.ReqSaveProfile;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/profile")
    public HttpResult saveProgress(@RequestBody ReqSaveProfile req) {
        User user = userService.queryUser(req.getId());
        if (user== null) {
            return HttpResult.fail(I18nConstants.COMMON_NOT_FOUND);
        }
        user.setAvatar(req.getAvatar());
        user.setUserName(req.getName());
        user.setSignature(req.getRemark());
        userService.saveUser(user);
        return HttpResult.ok();
    }
}
