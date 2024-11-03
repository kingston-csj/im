package pers.kinson.im.chat.logic.file;

import jforgame.commons.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import pers.kinson.im.chat.core.HttpResult;

@Controller
@RequestMapping("/file")

public class FileController {

    @Autowired
    FileUploadService uploadService;

    @PostMapping("/upload")
    @ResponseBody
    public HttpResult uploadPic(@RequestParam("file") MultipartFile file, @RequestParam("type") int type,
                                @RequestParam(value = "params", required = false) String params) {
        ReqUploadFile reqUploadFile = new ReqUploadFile();
        reqUploadFile.setType(type);
        reqUploadFile.setParams(params);
        return uploadService.uploadResource(file, reqUploadFile);
    }

}
