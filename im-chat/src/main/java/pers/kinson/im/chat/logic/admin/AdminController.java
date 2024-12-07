package pers.kinson.im.chat.logic.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.kinson.im.chat.config.ServerProperties;
import pers.kinson.im.chat.core.HttpResult;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    ServerProperties serverProperties;

    // curl -X POST -H "Content-Type: application/json" -d '{"versioin":"1.2.0"}' http://localhost:8080/admin/modifyClientVersion
    @PostMapping(value = "/modifyClientVersion")
    public HttpResult modifyClientVersion(@RequestBody ReqModifyClientVersion req) {
        serverProperties.setVersion(req.getVersion());
        return HttpResult.ok();
    }
}
