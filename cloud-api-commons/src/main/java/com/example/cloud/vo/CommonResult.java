package com.example.cloud.vo;

import lombok.Data;

@Data
public class CommonResult<T> {

    private String code;

    private String message;

    private T data;

}
