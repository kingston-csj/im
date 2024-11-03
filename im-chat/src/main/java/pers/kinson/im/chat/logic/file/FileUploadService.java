package pers.kinson.im.chat.logic.file;

import jakarta.annotation.PostConstruct;
import jforgame.commons.JsonUtil;
import jforgame.commons.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import pers.kinson.im.chat.core.HttpResult;
import pers.kinson.im.common.constants.I18nConstants;
import pers.kinson.im.common.logger.LoggerFunction;
import pers.kinson.im.common.logger.LoggerUtil;
import pers.kinson.im.oss.OssConfig;
import pers.kinson.im.oss.OssService;
import pers.kinson.im.oss.S3Client;
import pers.kinson.im.oss.UploadFileVo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileUploadService {


    @Autowired
    private OssConfig ossConfig;
    @Autowired
    private S3Client s3Client;

    @Autowired
    OssService ossService;

    private List<UploadResourceHandler> handlers = new ArrayList<>();

    @PostConstruct
    private void init() {
        handlers.add(new UserAvatarHandler());
    }


    /**
     * 资源上传入口
     */
    public HttpResult uploadResource(MultipartFile file, ReqUploadFile request) {
        // 多个解析器形成一条责任链，从头到尾依次匹配，若符合条件则选择为当前解析器
        // 最后一个节点为素材资源解析器，相当于默认值
        for (UploadResourceHandler handler : handlers) {
            if (handler.chosen(file, request)) {
                try {
                    ResUploadFile result = handler.handle(file, request);
                    return HttpResult.ok(JsonUtil.object2String(result));
                } catch (Exception e) {
                    LoggerUtil.error(String.format("上传文件[%s]失败", file.getName()), e);
                    return HttpResult.fail(I18nConstants.COMMON_INTERNAL_ERROR);
                }
            }
        }
        // 不可能跑到这里来
        throw new IllegalStateException("internal exception");
    }

    /**
     * 上传资源文件
     */
    public Pair<String, String> uploadResource(UploadFileVo file) throws Exception {
        if (file == null) {
            throw new RuntimeException("illegal file size");
        }

        String originalFileName = file.getFileName();
        String key = UUID.randomUUID().toString().replace("-", "");
        String suffix = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        String objectName = String.format("%s%s", key, "." + suffix);
        String fullPath = file.getCatalog() + "/" + objectName;

        s3Client.upload(file.getInputStream(), fullPath, file.getContentType());

        String url = ossService.fullOssPath(fullPath);
        LoggerUtil.info(LoggerFunction.UPLOAD, "fileName", originalFileName, "objectName", objectName, "size", file.getSize(), "fullPath", fullPath);
        return new Pair<>(fullPath, url);
    }
}
