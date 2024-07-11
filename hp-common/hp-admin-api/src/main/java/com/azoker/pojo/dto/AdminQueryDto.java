package com.azoker.pojo.dto;

import lombok.Data;

/**
 * Created by zxd on 2023/7/10
 */
@Data
public class AdminQueryDto extends BasePageDto{

    //管理员名称模糊查询
    private String name;



}
