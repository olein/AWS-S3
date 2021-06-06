package com.rnd.s3.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

@Data
public class ServiceResponse<T> implements Serializable{

    private HttpStatus status; //OK_Failure
    private StatusCode statusCode; //code
    private T body;
    private List<T> errorList;

    public ServiceResponse() {
    }

    public ServiceResponse(HttpStatus status, StatusCode statusCode, T body, List<T> errorList) {
        this.setStatus(status);
        this.setStatusCode(statusCode);
        this.setBody(body);
        this.errorList = errorList;
    }
}