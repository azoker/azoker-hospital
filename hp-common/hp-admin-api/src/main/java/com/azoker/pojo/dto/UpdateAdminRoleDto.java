package com.azoker.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zxd on 2023/3/30
 */
@Data
public class UpdateAdminRoleDto implements Serializable {

    //管理员id
    private Long adminId;

    //角色id集合
    private List<Long> roleIds;
}
