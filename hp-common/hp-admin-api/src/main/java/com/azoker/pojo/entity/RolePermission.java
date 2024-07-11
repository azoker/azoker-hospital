package com.azoker.pojo.entity;

import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by zxd on 2023/7/11
 */
@Data
@Table("fr_role_permission")
public class RolePermission implements Serializable {

    private Long roleId;

    private Long permissionId;

}
