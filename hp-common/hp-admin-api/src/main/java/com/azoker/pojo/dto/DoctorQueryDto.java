package com.azoker.pojo.dto;

import lombok.Data;

/**
 * Created by zxd on 2023/7/7
 */
@Data
public class DoctorQueryDto extends BasePageDto{


    //医生名称模糊查询
    private String name;

    //科室id
    private Long depId;

    //诊室id
    private Long conId;

}
