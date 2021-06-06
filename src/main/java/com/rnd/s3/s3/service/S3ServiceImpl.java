package com.rnd.s3.s3.service;

import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.rnd.s3.model.ServiceResponse;
import com.rnd.s3.s3.config.AmazonS3Client;
import com.rnd.s3.util.Util;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/*
Fahim created at 6/5/2021
*/
@Service
@Log4j2
public class S3ServiceImpl implements S3Service {

    String bucketName = "fahim-s3-rnd";

    @Autowired
    AmazonS3Client amazonS3Client;

    @Override
    public ServiceResponse uploadFile(MultipartFile file) throws IOException {

        amazonS3Client.uploadFile(bucketName, file);

        return Util.prepareSuccessResponse("File Uploaded successfully");
    }

    @Override
    public ServiceResponse getDownloadUrl(String fileName) {
        String url = amazonS3Client.getPreSignedDownloadUrl(bucketName, fileName);
        return Util.prepareSuccessResponse(url);
    }

    @Override
    public ServiceResponse deleteFile(String fileName) {

        amazonS3Client.deleteFile(bucketName, fileName);
        return Util.prepareSuccessResponse("File Deleted successfully");
    }
}
