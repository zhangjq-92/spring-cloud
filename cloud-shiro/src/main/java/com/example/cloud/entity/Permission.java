package com.example.cloud.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Permission {
    private Long id;

    private String code;

    private String description;

    private String url;
}
