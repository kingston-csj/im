package pers.kinson.im.oss;

import io.minio.CopyObjectArgs;
import io.minio.CopySource;
import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.http.Method;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public class MinIoS3Client implements S3Client {

    private MinioClient minioClient;

    private OssConfig ossConfig;

    public MinIoS3Client(MinioClient minioClient, OssConfig ossConfig) {
        this.minioClient = minioClient;
        this.ossConfig = ossConfig;
    }

    @Override
    public void createBucket(String name) throws OssException {
        try {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(name).build());
        } catch (Exception e) {
            throw new OssException(e);
        }
    }

    @Override
    public String upload(InputStream input, String filePath, String contentType) throws OssException {
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(ossConfig.getBucketName())
                            .object(filePath).stream(input, input.available(), -1)
                            .contentType(contentType)
                            .build());
            return filePath;
        } catch (Exception e) {
            throw new OssException(e);
        }
    }

    @Override
    public InputStream getObject(String fileName) throws OssException {
        GetObjectArgs request = GetObjectArgs.builder().bucket(ossConfig.getBucketName()).object(fileName).build();
        try {
            return minioClient.getObject(request);
        } catch (Exception e) {
            throw new OssException(e);
        }
    }

    @Override
    public void remove(String objectName) throws OssException {
        RemoveObjectArgs request = RemoveObjectArgs.builder().bucket(ossConfig.getBucketName()).object(objectName).build();
        try {
            minioClient.removeObject(request);
        } catch (Exception e) {
            throw new OssException(e);
        }
    }

    @Override
    public void copyResource(String copy, String target) throws OssException {
        try {
            String bucket = ossConfig.getBucketName();
            minioClient.copyObject(
                    CopyObjectArgs.builder()
                            .bucket(bucket)
                            .object(target)
                            .source(
                                    CopySource.builder()
                                            .bucket(bucket)
                                            .object(copy)
                                            .build())
                            .build());
        } catch (Exception e) {
            throw new OssException(e);
        }
    }

    @Override
    public String generatePresignedUrl(String path, String contentType) throws OssException {
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.PUT)
                            .bucket(ossConfig.getBucketName())
                            .object(path)
                            .expiry(1, TimeUnit.HOURS)
                            .build());
        } catch (Exception e) {
            throw new OssException(e);
        }
    }

}