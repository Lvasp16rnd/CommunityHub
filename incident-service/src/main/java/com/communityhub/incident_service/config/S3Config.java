package com.communityhub.incident_service.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Configuration
public class S3Config {

    private final S3Client s3Client;
    private final String bucketName = "communityhub-incidents";

    public S3Config(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void createBucketOnInit() {

        try {

            s3Client.headBucket(HeadBucketRequest.builder().bucket(bucketName).build());

            System.out.println("✅ Bucket '" + bucketName + "' já existe no LocalStack. Tudo pronto!");
        } catch (S3Exception e) {

            if (e.statusCode() == 404) {

                s3Client.createBucket(CreateBucketRequest.builder().bucket(bucketName).build());

                System.out.println("🚀 Bucket '" + bucketName + "' criado com sucesso no LocalStack!");
            } else {
                throw e;
            }
        }
    }
}
