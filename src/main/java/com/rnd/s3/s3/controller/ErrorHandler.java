package com.rnd.s3.s3.controller;

import com.rnd.s3.model.ServiceResponse;
import com.rnd.s3.model.StatusCode;
import com.rnd.s3.util.ErrorUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
Fahim created at 3/27/2021
*/
@ControllerAdvice
@Log4j2
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { Exception.class})
    protected ResponseEntity<Object> handleInternalServerError(
            Exception ex, WebRequest request) {
        log.error(ex.getMessage());
        ServiceResponse serviceResponse = new ServiceResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                StatusCode.ERROR, null, ErrorUtil.prepareInternalServerErrorResponse());
        return handleExceptionInternal(ex, serviceResponse,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = { IllegalArgumentException.class})
    protected ResponseEntity<Object> handleBadRequest(
            Exception ex, WebRequest request) {
        log.error(ex.getMessage());
        ServiceResponse serviceResponse = new ServiceResponse(HttpStatus.BAD_REQUEST,
                StatusCode.ERROR, null, ErrorUtil.prepareErrorResponse(ex.getMessage()));
        return handleExceptionInternal(ex, serviceResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
