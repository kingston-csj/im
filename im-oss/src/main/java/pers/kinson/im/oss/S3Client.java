package pers.kinson.im.oss;

import java.io.InputStream;

public interface S3Client {

    /**
     * 创建数据桶
     * @param name 桶名
     */
    void createBucket(String name) throws OssException;

    /**
     * 创建数据桶
     * @param filePath s3内部路径
     */
    String upload(InputStream input, String filePath, String contentType) throws OssException;

    /**
     * 获取指定对象输入流
     */
    InputStream getObject(String fileName) throws OssException;

    /**
     * 删除对象
     */
    void remove(String objectName) throws OssException;

    /**
     * 复制对象，只支持同一个桶内部复制（简化API）
     */
    void copyResource(String copy, String target) throws OssException;

    /**
     * 生成客户端临时上传路径
     */
    String generatePresignedUrl(String path) throws OssException;
}
