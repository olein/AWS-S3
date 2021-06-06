package com.rnd.s3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/*
Fahim created at 3/12/2021
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorModel implements Serializable {

    private String message;
    private String field;
    private String description;

    public ErrorModel(String message) {
        this.message = message;
    }
}
