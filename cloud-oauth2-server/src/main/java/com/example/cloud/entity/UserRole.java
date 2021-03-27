package com.example.cloud.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRole {

    private Long id;

    private Long userId;

    private Long roleId;
}
