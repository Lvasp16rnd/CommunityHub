package com.communityhub.incident_service.infrastructure;

import java.util.UUID;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectAclRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest.Builder;
import software.amazon.awssdk.core.sync.RequestBody;

@Service
public class S3StorageService {

    private final S3Client s3Client;

    private final String bucketName = "communityhub-incidents";

    public S3StorageService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadImage(MultipartFile file) throws IOException {

        // 1. O Nome do Arquivo: Junta o UUID com o nome original da foto
        String filename = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

        // 2. O Padrão Builder: Montando o pedido para a AWS de forma elegante
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName) // Qual é o balde?
                .key(filename) // Qual é o nome do arquivo?
                .contentType(file.getContentType()) // É um JPG? PNG?
                .build(); // Constrói o pedido final!

        // 3. O Envio: Passamos o pedido e o corpo do arquivo (os bytes)
        s3Client.putObject(
                putObjectRequest,
                RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

        // 4. O Retorno: A URL mágica que vamos salvar no MongoDB
        return "http://localhost:4566/" + bucketName + "/" + filename;
    }
}