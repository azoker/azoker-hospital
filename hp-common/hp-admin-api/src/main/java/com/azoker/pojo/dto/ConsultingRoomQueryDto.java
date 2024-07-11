package com.azoker.pojo.dto;

import lombok.Data;

/**
 * Created by zxd on 2023/7/5
 */
@Data
public class ConsultingRoomQueryDto extends BasePageDto{

    //诊室名称模糊查询
    private String name;

    //科室id
    private Long depId;

}
