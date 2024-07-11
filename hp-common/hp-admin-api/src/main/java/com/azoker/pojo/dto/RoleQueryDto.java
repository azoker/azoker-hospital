package com.azoker.pojo.dto;

import lombok.Data;

/**
 * Created by zxd on 2023/7/11
 */
@Data
public class RoleQueryDto extends BasePageDto{

    //角色名称模糊查询
    private String name;

}
