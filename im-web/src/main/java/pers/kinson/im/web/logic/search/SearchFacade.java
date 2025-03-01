package pers.kinson.im.web.logic.search;

import jforgame.commons.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.kinson.im.common.HttpResult;
import pers.kinson.im.web.logic.search.message.req.ReqSearchFriends;
import pers.kinson.im.web.logic.search.message.vo.RecommendFriendItem;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchFacade {

    @Autowired
    SearchService searchService;

    @GetMapping(value = "/key")
    public HttpResult queryList(ReqSearchFriends req) {
        List<RecommendFriendItem> result = searchService.search(req.getKey());
        return HttpResult.ok(JsonUtil.object2String(result));
    }

}
