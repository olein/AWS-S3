package com.rnd.s3.s3.controller;

import com.rnd.s3.model.ServiceResponse;
import com.rnd.s3.s3.service.S3Service;
import com.rnd.s3.util.ErrorUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/*
Fahim created at 6/5/2021
*/
@RestController
@RequestMapping("/api/s3")
@Log4j2
public class S3Controller {

    @Autowired
    S3Service s3Service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ServiceResponse> uploadFile(@RequestPart(value = "file") MultipartFile file) throws IOException {
        ServiceResponse response;

        if (file.isEmpty()) {
            log.error("File Upload failed");
            response = ErrorUtil.prepareErrorResponse("No file uploaded", HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(response);
        }

        response = s3Service.uploadFile(file);

        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "/downloadUrl/{fileName}", method = RequestMethod.GET)
    public ResponseEntity<ServiceResponse> downloadFile(@PathVariable(value = "fileName") String fileName) {
        ServiceResponse response;

        if (StringUtils.isEmpty(fileName)) {
            log.error("No file name provided");
            response = ErrorUtil.prepareErrorResponse("No file name provided", HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(response);
        }

        response = s3Service.getDownloadUrl(fileName);

        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "/{fileName}", method = RequestMethod.DELETE)
    public ResponseEntity<ServiceResponse> deleteFile(@PathVariable(value = "fileName") String fileName) {
        ServiceResponse response;

        if (StringUtils.isEmpty(fileName)) {
            log.error("No file name provided");
            response = ErrorUtil.prepareErrorResponse("No file name provided", HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(response);
        }

        response = s3Service.deleteFile(fileName);

        return ResponseEntity.ok(response);
    }
}
