package com.education.platform.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface FileStorageService {
    String storeFile(MultipartFile file, String directory) throws IOException;
    void deleteFile(String fileUrl) throws IOException;
    byte[] getFile(String fileUrl) throws IOException;
    String getFileUrl(String fileName);
} 