package com.azoker.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zxd on 2023/7/11
 */
@Data
public class UpdateRolePermissionDto implements Serializable {

    //角色id
    private Long roleId;

    //权限id集合
    private List<Long> permissionIds;

}
