package com.halfsay.flower.pojo;

import lombok.Data;

@Data
public class Result {
    private Integer statusCode = 200;
    private String message;
    private Object data;
}
