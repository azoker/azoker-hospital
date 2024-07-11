package com.azoker.pojo.dto;


import lombok.Data;

/**
 * 角色菜单mapper接口
 * Created by zxd on 2022/8/3
 */
@Data
public class MenuDTO {

    private Long id;
    private String name;
    private String perms;
    private String path;
    private Boolean isFrame;
    private Long parentId;
    private String component;
    private String icon;
    private Integer sort;
    private Integer type;
    private String delFlag;


}
