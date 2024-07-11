package com.azoker.pojo.dto;

import lombok.Data;

/**
 * Created by zxd on 2023/7/5
 */
@Data
public class DepartmentQueryDto extends BasePageDto {

    //添加额外的条件

    //科室名称模糊查询
    private String name;

    //科室类型查询  1：门诊   0非门诊
    private Integer type;

    //推荐级别查询  1:首页推荐 0：不推荐
    private Integer recommended;


}
