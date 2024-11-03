package pers.kinson.im.chat.logic.file;

import jforgame.commons.NumberUtil;
import jforgame.commons.Pair;
import org.springframework.web.multipart.MultipartFile;
import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.data.model.User;
import pers.kinson.im.chat.logic.user.UserService;
import pers.kinson.im.common.constants.I18nConstants;
import pers.kinson.im.common.exception.BusinessRequestException;
import pers.kinson.im.oss.OssService;
import pers.kinson.im.oss.UploadFileVo;

import java.io.InputStream;

public interface UploadResourceHandler {

    /**
     * 多个解析器形成一条责任链，从头到尾依次匹配，若符合条件则选择为当前解析器
     *
     * @param file    文件流
     * @param request 游戏id
     * @return 匹配成功
     */
    boolean chosen(MultipartFile file, ReqUploadFile request);

    ResUploadFile handle(MultipartFile file, ReqUploadFile request) throws Exception;
}

class UserAvatarHandler implements UploadResourceHandler {

    @Override
    public boolean chosen(MultipartFile file, ReqUploadFile request) {
        //  最后一个节点，兜底操作，来者不拒
        return true;
    }

    @Override
    public ResUploadFile handle(MultipartFile file, ReqUploadFile request) throws Exception {
        UploadFileVo fileVo = UploadFileVo.builder().fileName(file.getOriginalFilename())
                .contentType(file.getContentType()).size(file.getSize())
                .build();

        InputStream inputStream = file.getInputStream();
        fileVo.setInputStream(inputStream);

        String suffix =  suffixType(fileVo.getFileName());
        OssService ossService = SpringContext.getBean(OssService.class);
        if (!ossService.isPicture(suffix)) {
            throw new BusinessRequestException(I18nConstants.COMMON_ILLEGAL_PARAMS);
        }
        long userId = NumberUtil.longValue(request.getParams());
        User user = SpringContext.getBean(UserService.class).getOnlineUser(userId);
        if (user == null) {
            throw new BusinessRequestException(I18nConstants.COMMON_ILLEGAL_PARAMS);
        }
        // 根据文件类型选择目录
        String catalog = ossService.getPathOf(suffix);
        fileVo.setCatalog(catalog);

        FileUploadService fileService = SpringContext.getBean(FileUploadService.class);
        Pair<String, String> urls = fileService.uploadResource(fileVo);
        user.setAvatar(urls.getFirst());
        SpringContext.getUserService().saveUser(user);

        return ResUploadFile.builder()
                .url(urls.getSecond())
                .name(fileVo.getFileName()).build();
    }


     String suffixType(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }
}