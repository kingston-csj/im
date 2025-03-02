package pers.kinson.im.web.logic.user;

import io.netty.channel.Channel;
import jforgame.commons.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.kinson.business.entity.User;
import pers.kinson.im.common.HttpResult;
import pers.kinson.im.common.constants.I18nConstants;
import pers.kinson.im.web.logic.login.message.req.ReqUserLogin;
import pers.kinson.im.web.logic.search.SearchService;
import pers.kinson.im.web.logic.search.message.req.ReqSearchFriends;
import pers.kinson.im.web.logic.search.message.vo.RecommendFriendItem;
import pers.kinson.im.web.logic.user.message.req.ReqSaveProfile;
import pers.kinson.im.web.logic.user.message.req.ReqUserRegister;

import java.util.List;

@RestController
@RequestMapping("/account")
public class UserFacade {

    @Autowired
    private UserService userService;


    @PostMapping(value = "/register")
    public HttpResult action(ReqUserRegister req) {
        return userService.registerNewAccount(req.getSex(), req.getUserId(), req.getPassword());
    }

    @Autowired
    private SearchService searchService;

    @PostMapping(value = "/search")
    public HttpResult reqSearchFriends(ReqSearchFriends req) {
        List<RecommendFriendItem> friends = searchService.search(req.getKey());
        return HttpResult.ok(JsonUtil.object2String(friends));
    }

    @PostMapping(value = "/login")
    public HttpResult validateLogin(@RequestBody ReqUserLogin req) {
        User user = userService.validateUser(req.getUserId(), req.getUserPwd());
        if (user == null) {
            return HttpResult.fail(I18nConstants.COMMON_NOT_FOUND);
        }
        return HttpResult.ok();
    }

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
