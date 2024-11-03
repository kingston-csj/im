package pers.kinson.im.oss;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OssAutoConfig {

    @ConditionalOnProperty(name = "s3.type", havingValue = "minio")
    public static class MinioAutoConfig {

        @Autowired
        private OssConfig ossConfig;

        @Bean
        public S3Client createS3Client() throws Exception {
            MinioClient minioClient = minioClient();
            return new MinIoS3Client(minioClient, ossConfig);
        }

        private MinioClient minioClient() throws Exception {
            return MinioClient.builder().endpoint(ossConfig.getEndpoint())
                    .region(ossConfig.getRegion())
                    .credentials(ossConfig.getAccessKey(), ossConfig.getSecretKey())
                    .build();
        }
    }

    @ConditionalOnProperty(name = "s3.type", havingValue = "amazon")
    public static class AmazonAutoConfig {
        @Autowired
        private OssConfig ossConfig;

        @Bean
        public S3Client createS3Client() throws Exception {
            AWSCredentials credentials = new BasicAWSCredentials(ossConfig.getAccessKey(), ossConfig.getSecretKey());
            AwsClientBuilder.EndpointConfiguration endpointConfig = new AwsClientBuilder.EndpointConfiguration(
                    ossConfig.getEndpoint(), // amazon统一为s3.amazonaws.com，MinIO可自定义
                    ossConfig.getRegion()// 签名区域，对于 MinIO 来说，这个值可以是任意字符串
            );
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .withEndpointConfiguration(endpointConfig)
                    .build();

            return new AmazonS3Client(s3Client, ossConfig);
        }
    }
}
