package pers.kinson.im.chat.logic.script;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import pers.kinson.im.chat.data.dao.OssResourceDao;
import pers.kinson.im.chat.data.model.OssResource;
import pers.kinson.im.common.logger.LoggerUtil;
import pers.kinson.im.common.utils.FileMD5Calculator;
import pers.kinson.im.common.utils.IdFactory;
import pers.kinson.im.oss.FileTypes;
import pers.kinson.im.oss.OssService;
import pers.kinson.im.oss.S3Client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class EmojiScript {

    @Autowired
    S3Client s3Client;

    @Autowired
    OssResourceDao ossResourceDao;

    @Autowired
    OssService ossService;

    public void updateEmojiResource() {
        Map<String, OssResource> existed = ossResourceDao.selectList(new LambdaQueryWrapper<>()).stream().collect(Collectors.toMap(OssResource::getLabel, Function.identity()));
        String folderUrl = "D:\\java_projects\\im\\im\\im-chat\\src\\test\\resources\\emoji";
        Map<String, FileVo> newFiles = listFiles(folderUrl);

        newFiles.forEach((key, value) -> {
            // 新增
            if (!existed.containsKey(key)) {
                String id = IdFactory.nextUUId();
                try {
                    doUpload(value.file, id);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                OssResource prev = existed.get(key);
                // md5不一致，表情内容有修改，替换
                if (!value.md5.equals(prev.getMd5())) {
                    try {
                        doReplace(value, prev);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    private Map<String, FileVo> listFiles(String directoryPath) {
        Map<String, FileVo> files = new HashMap<>();
        try {
            Resource resource = new FileSystemResource(directoryPath);
            File directory = resource.getFile();

            if (directory.exists()) {
                File[] fileList = directory.listFiles();
                if (fileList != null) {
                    for (File file : fileList) {
                        FileVo fileVo = new FileVo(file);
                        files.put(fileVo.label, fileVo);
                    }
                }
            }
        } catch (Exception e) {
            LoggerUtil.error("", e);
        }
        return files;
    }

    static class FileVo {
        File file;

        String md5;

        String label;

        public FileVo(File file) throws Exception {
            this.file = file;
            this.md5 = FileMD5Calculator.calculateMD5(file);
            this.label = file.getName().substring(0, file.getName().lastIndexOf("."));
        }
    }

    private void doUpload(File file, String id) throws IOException {
        try {
            String suffix = file.getName().substring(file.getName().lastIndexOf(".") + 1);
            String catalog = FileTypes.EMOJI.getPath() + "/";
            String objectName = String.format("%s%s", id, "." + suffix);
            String fullPath = catalog + objectName;
            s3Client.upload(new FileInputStream(file), fullPath, ossService.getContentType(suffix));
            OssResource ossResource = new OssResource();
            ossResource.setUrl(fullPath);
            ossResource.setOriginalName(file.getName());
            ossResource.setType(FileTypes.EMOJI.getPath());
            ossResource.setLabel(file.getName().substring(0, file.getName().lastIndexOf(".")));
            ossResource.setCreatedDate(new Date());
            ossResource.setMd5(FileMD5Calculator.calculateMD5(file));
            ossResourceDao.insert(ossResource);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    private void doReplace(FileVo fileVo, OssResource ossResource) throws IOException {
        try {
            String fileName = fileVo.file.getName();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            String oldPath = ossResource.getUrl();
            // 删掉旧的
            s3Client.remove(oldPath);
            String catalog = FileTypes.EMOJI.getPath() + "/";
            String objectName = String.format("%s%s", IdFactory.nextUUId(), "." + suffix);
            String newPath = catalog + objectName;;
            // 上传新的
            s3Client.upload(new FileInputStream(fileVo.file), oldPath, ossService.getContentType(suffix));
            ossResource.setUrl(oldPath);
            ossResource.setUrl(newPath);
            ossResource.setOriginalName(fileName);
            ossResource.setType(FileTypes.EMOJI.getPath());
            ossResource.setLabel(fileVo.label);
            ossResource.setCreatedDate(new Date());
            ossResource.setMd5(fileVo.md5);
            ossResourceDao.updateById(ossResource);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

}
