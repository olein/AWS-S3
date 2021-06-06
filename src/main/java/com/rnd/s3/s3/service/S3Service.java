package com.rnd.s3.s3.service;

import com.rnd.s3.model.ServiceResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface S3Service {
    ServiceResponse uploadFile(MultipartFile file) throws IOException;

    ServiceResponse getDownloadUrl(String fileName);

    ServiceResponse deleteFile(String fileName);
}
