package com.example.cloud.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RolePermission {

    private Long id;

    private Long roleId;

    private Long permissionId;

}
