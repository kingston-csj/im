package pers.kinson.im.web.logic.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.kinson.im.common.HttpResult;
import pers.kinson.im.oss.OssService;
import pers.kinson.im.web.config.ServerProperties;

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
