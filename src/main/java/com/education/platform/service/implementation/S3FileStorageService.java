package com.education.platform.service.implementation;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.education.platform.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3FileStorageService implements FileStorageService {

    private final AmazonS3 s3Client;

    @Value("${aws.bucketName}")
    private String bucketName;

    @Override
    public String storeFile(MultipartFile file, String directory) throws IOException {
        String fileName = generateFileName(file.getOriginalFilename());
        String fileKey = directory + "/" + fileName;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        PutObjectRequest putObjectRequest = new PutObjectRequest(
                bucketName,
                fileKey,
                file.getInputStream(),
                metadata
        );

        s3Client.putObject(putObjectRequest);
        return getFileUrl(fileKey);
    }

    @Override
    public void deleteFile(String fileUrl) throws IOException {
        String fileKey = getFileKeyFromUrl(fileUrl);
        s3Client.deleteObject(bucketName, fileKey);
    }

    @Override
    public byte[] getFile(String fileUrl) throws IOException {
        String fileKey = getFileKeyFromUrl(fileUrl);
        S3Object s3Object = s3Client.getObject(bucketName, fileKey);
        return s3Object.getObjectContent().readAllBytes();
    }

    @Override
    public String getFileUrl(String fileName) {
        return s3Client.getUrl(bucketName, fileName).toString();
    }

    private String generateFileName(String originalFileName) {
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        return UUID.randomUUID().toString() + extension;
    }

    private String getFileKeyFromUrl(String fileUrl) {
        return fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
    }
} 