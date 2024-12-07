package pers.kinson.im.chat.logic.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.kinson.im.chat.config.ServerProperties;
import pers.kinson.im.chat.core.HttpResult;
import pers.kinson.im.oss.OssService;

@RestController
@RequestMapping("/system")
public class SystemController {

    @Autowired
    ServerProperties serverProperties;
    @Autowired
    OssService ossService;

    @GetMapping(value = "/version")
    public HttpResult getVersion() {
        return HttpResult.ok(serverProperties.getVersion());
    }

    @GetMapping(value = "/clientApp")
    public HttpResult getClientApp() {
        return HttpResult.ok(ossService.fullOssPath("other/wechat.jar"));
    }

}
