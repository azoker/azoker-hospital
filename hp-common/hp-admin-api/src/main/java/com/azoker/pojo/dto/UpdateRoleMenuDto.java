package com.azoker.pojo.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by zxd on 2022/8/3
 */
@Data
public class UpdateRoleMenuDto {


    /**
     * 角色id
     */
    private Long id;

    /**
     * 角色用有的菜单ID
     */
    private List<Long> menuIds;


}
