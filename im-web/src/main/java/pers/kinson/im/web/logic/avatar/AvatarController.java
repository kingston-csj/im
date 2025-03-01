package pers.kinson.im.web.logic.avatar;

import jforgame.commons.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.kinson.im.common.HttpResult;

@RestController
@RequestMapping("/avatar")
public class AvatarController {

    @Autowired
    AvatarService avatarService;

    @GetMapping(value = "/list")
    public HttpResult list() {
        return HttpResult.ok(JsonUtil.object2String(avatarService.listAllAvatar()));
    }
}
