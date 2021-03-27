package com.example.cloud.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TestUser {

    private Long id;

    private String userName;

    private String password;
}
