package com.example.cloud.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Payment implements Serializable {

    private Long id;

    private String serial;


}
