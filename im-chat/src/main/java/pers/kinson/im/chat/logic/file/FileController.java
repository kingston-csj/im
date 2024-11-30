package pers.kinson.im.chat.logic.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.kinson.im.chat.core.HttpResult;
import pers.kinson.im.chat.logic.file.message.req.ReqUploadFile;

@Controller
@RequestMapping("/file")

public class FileController {

    @Autowired
    FileUploadService uploadService;

    @PostMapping("/upload")
    @ResponseBody
    public HttpResult uploadResource(@RequestParam("file") String file, @RequestParam("type") int type,
                                @RequestParam(value = "params", required = false) String params) {
        ReqUploadFile reqUploadFile = new ReqUploadFile();
        reqUploadFile.setType(type);
        reqUploadFile.setParams(params);
        return uploadService.uploadResource(file, reqUploadFile);
    }

}
