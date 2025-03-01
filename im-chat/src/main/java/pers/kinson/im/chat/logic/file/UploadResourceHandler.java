package pers.kinson.im.chat.logic.file;

import jforgame.commons.NumberUtil;
import jforgame.commons.Triple;
import pers.kinson.business.entity.OssResource;
import pers.kinson.business.entity.User;
import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.data.dao.OssResourceDao;
import pers.kinson.im.chat.logic.file.message.req.ReqUploadFile;
import pers.kinson.im.chat.logic.file.message.res.ResUploadFile;
import pers.kinson.im.chat.logic.user.UserService;
import pers.kinson.im.common.constants.I18nConstants;
import pers.kinson.im.common.exception.BusinessRequestException;
import pers.kinson.im.common.logger.LoggerFunction;
import pers.kinson.im.common.logger.LoggerUtil;
import pers.kinson.im.common.utils.IdFactory;
import pers.kinson.im.oss.OssService;
import pers.kinson.im.oss.S3Client;
import pers.kinson.im.oss.UploadFileVo;

import java.util.Date;

public interface UploadResourceHandler {

    /**
     * 多个解析器形成一条责任链，从头到尾依次匹配，若符合条件则选择为当前解析器
     *
     * @param file    文件流
     * @param request 游戏id
     * @return 匹配成功
     */
    boolean chosen(String file, ReqUploadFile request);

    ResUploadFile handle(String file, ReqUploadFile request) throws Exception;


    default String suffixType(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }

    /**
     * 上传资源文件
     */
    default Triple<String, String, String> uploadResource(String catalog, String originalFileName) {
        String suffix = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        String key = IdFactory.nextUUId();
        String objectName = String.format("%s%s", key, "." + suffix);
        String fullPath = catalog + "/" + objectName;
        OssService ossService = SpringContext.getBean(OssService.class);
        String contentType = ossService.getContentType(suffix);
        String presignedUrl = SpringContext.getBean(S3Client.class).generatePresignedUrl(fullPath, contentType);

        OssResource ossResource = OssResource.builder().type(ossService.getPathOf(suffix)).createdDate(new Date()).originalName(originalFileName).url(fullPath).build();
        SpringContext.getBean(OssResourceDao.class).insert(ossResource);
        LoggerUtil.info(LoggerFunction.UPLOAD, "fileName", originalFileName, "objectName", objectName, "fullPath", fullPath);
        return new Triple<>(objectName, ossService.fullOssPath(fullPath), presignedUrl);
    }
}

class ChatMediaFileHandler implements UploadResourceHandler {

    @Override
    public boolean chosen(String file, ReqUploadFile request) {
        return request.getType() == 1;
    }

    @Override
    public ResUploadFile handle(String file, ReqUploadFile request) throws Exception {
        String suffix = suffixType(file);
        OssService ossService = SpringContext.getBean(OssService.class);
        // 根据文件类型选择目录
        String catalog = ossService.getPathOf(suffix);
        Triple<String, String, String> urls = uploadResource(catalog, file);

        return ResUploadFile.builder()
                .cdnUrl(urls.getSecond())
                .presignedUrl(urls.getThird())
                .name(file).build();
    }

}

class UserAvatarHandler implements UploadResourceHandler {

    @Override
    public boolean chosen(String file, ReqUploadFile request) {
        return request.getType() == 2;
    }

    @Override
    public ResUploadFile handle(String file, ReqUploadFile request) throws Exception {
        UploadFileVo fileVo = UploadFileVo.builder().fileName(file)
                .build();
        String suffix = suffixType(fileVo.getFileName());
        OssService ossService = SpringContext.getBean(OssService.class);
        if (!ossService.isPicture(suffix)) {
            throw new BusinessRequestException(I18nConstants.COMMON_ILLEGAL_PARAMS);
        }
        long userId = NumberUtil.longValue(request.getParams());
        User user = SpringContext.getBean(UserService.class).queryUser(userId);
        if (user == null) {
            throw new BusinessRequestException(I18nConstants.COMMON_ILLEGAL_PARAMS);
        }
        // 根据文件类型选择目录
        String catalog = ossService.getPathOf(suffix);
        fileVo.setCatalog(catalog);

        Triple<String, String, String> urls = uploadResource(catalog, file);
        user.setAvatar(urls.getSecond());
        SpringContext.getUserService().saveUser(user);

        return ResUploadFile.builder()
                .cdnUrl(urls.getSecond())
                .name(fileVo.getFileName()).build();
    }

}