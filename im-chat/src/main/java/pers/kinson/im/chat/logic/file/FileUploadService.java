package pers.kinson.im.chat.logic.file;

import jakarta.annotation.PostConstruct;
import jforgame.commons.JsonUtil;
import org.springframework.stereotype.Component;
import pers.kinson.im.chat.core.HttpResult;
import pers.kinson.im.chat.logic.file.message.req.ReqUploadFile;
import pers.kinson.im.chat.logic.file.message.res.ResUploadFile;
import pers.kinson.im.common.constants.I18nConstants;
import pers.kinson.im.common.logger.LoggerUtil;

import java.util.ArrayList;
import java.util.List;

@Component
public class FileUploadService {


    private List<UploadResourceHandler> handlers = new ArrayList<>();

    @PostConstruct
    private void init() {
        handlers.add(new UserAvatarHandler());
        handlers.add(new ChatMediaFileHandler());
    }

    /**
     * 资源上传入口
     */
    public HttpResult uploadResource(String file, ReqUploadFile request) {
        // 多个解析器形成一条责任链，从头到尾依次匹配，若符合条件则选择为当前解析器
        // 最后一个节点为素材资源解析器，相当于默认值
        for (UploadResourceHandler handler : handlers) {
            if (handler.chosen(file, request)) {
                try {
                    ResUploadFile result = handler.handle(file, request);
                    return HttpResult.ok(JsonUtil.object2String(result));
                } catch (Exception e) {
                    LoggerUtil.error(String.format("上传文件[%s]失败", file), e);
                    return HttpResult.fail(I18nConstants.COMMON_INTERNAL_ERROR);
                }
            }
        }
        // 不可能跑到这里来
        throw new IllegalStateException("internal exception");
    }


}
