package pers.kinson.im.chat.logic.user;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.core.HttpResult;
import pers.kinson.im.chat.data.model.User;
import pers.kinson.im.chat.logic.user.message.req.ReqSaveProfile;
import pers.kinson.im.common.constants.I18nConstants;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping(value = "/profile")
    public HttpResult saveProgress(@RequestBody ReqSaveProfile req) {
        User user = SpringContext.getUserService().getOnlineUser(req.getId());
        if (user== null) {
            return HttpResult.fail(I18nConstants.COMMON_NOT_FOUND);
        }
        user.setAvatar(req.getAvatar());
        user.setUserName(req.getName());
        user.setSignature(req.getRemark());
        SpringContext.getUserService().saveUser(user);
        return HttpResult.ok();
    }
}
