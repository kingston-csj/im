package pers.kinson.im.web.logic.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.kinson.im.common.HttpResult;
import pers.kinson.im.web.config.ServerProperties;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    ServerProperties serverProperties;

    // curl -X POST -H "Content-Type: application/json" -d '{"version":"1.2.0"}' http://localhost:8080/admin/modifyClientVersion
    @PostMapping(value = "/modifyClientVersion")
    public HttpResult modifyClientVersion(@RequestBody ReqModifyClientVersion req) {
        serverProperties.setVersion(req.getVersion());
        return HttpResult.ok(serverProperties.getVersion());
    }
}
