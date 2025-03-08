package pers.kinson.im.web.logic.user;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import jforgame.commons.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.kinson.business.entity.User;
import pers.kinson.im.common.HttpResult;
import pers.kinson.im.common.constants.I18nConstants;
import pers.kinson.im.common.logger.LoggerUtil;
import pers.kinson.im.infrastructure.security.AuthenticAccountDetailsService;
import pers.kinson.im.web.logic.search.SearchService;
import pers.kinson.im.web.logic.search.message.req.ReqSearchFriends;
import pers.kinson.im.web.logic.search.message.vo.RecommendFriendItem;
import pers.kinson.im.web.logic.user.message.req.ReqSaveProfile;
import pers.kinson.im.web.logic.user.message.req.ReqUserRegister;
import pers.kinson.im.web.logic.user.message.vo.SocketNodeInfo;

import java.util.List;
import java.util.Properties;
import java.util.Random;

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

    @PostMapping(value = "/profile")
    public HttpResult saveProgress(@RequestBody ReqSaveProfile req) {
        User user = userService.queryUser(req.getId());
        if (user == null) {
            return HttpResult.fail(I18nConstants.COMMON_NOT_FOUND);
        }
        user.setAvatar(req.getAvatar());
        user.setUserName(req.getName());
        user.setSignature(req.getRemark());
        userService.saveUser(user);
        return HttpResult.ok();
    }

    @Autowired
    AuthenticAccountDetailsService authenticAccountDetailsService;

    @PostMapping(value = "/login")
    public HttpResult login() {
        String currentUser = authenticAccountDetailsService.getCurrentUser();
        System.out.println(currentUser);
        SocketNodeInfo node = getSocketInstances();
        if (node == null) {
            return HttpResult.fail(I18nConstants.COMMON_NOT_FOUND);
        }
        return HttpResult.ok(JsonUtil.object2String(node));
    }

    @Autowired
    Environment environment;

    private SocketNodeInfo getSocketInstances() {
        String nacosServerAddress = environment.getProperty("spring.cloud.nacos.config.server-addr"); // Nacos服务器地址
        String serviceName = "socket"; // 服务名

        // 设置Nacos的属性
        Properties properties = new Properties();
        properties.put("serverAddr", nacosServerAddress);
        // 设置命名空间
        String namespace = environment.getProperty("spring.cloud.nacos.config.namespace");
        properties.put("namespace", namespace);
        String groupName = "im";

        // 获取NamingService实例
        try {
            NamingService namingService = NacosFactory.createNamingService(properties);
            List<Instance> allInstances = namingService.getAllInstances(serviceName, groupName);
            if (allInstances.isEmpty()) {
                return null;
            }
            // socket实例无法直接使用WebClient或者RestTemplate进行轮询， 这里手动随机获取一个实例
            Instance instance = allInstances.get(new Random().nextInt(allInstances.size()));
            SocketNodeInfo socketNodeInfo = new SocketNodeInfo();
            socketNodeInfo.setIp(instance.getIp());
            socketNodeInfo.setPort(instance.getPort());
            return socketNodeInfo;
        } catch (NacosException e) {
            LoggerUtil.error("", e);
            throw new RuntimeException(e);
        }
    }

}
