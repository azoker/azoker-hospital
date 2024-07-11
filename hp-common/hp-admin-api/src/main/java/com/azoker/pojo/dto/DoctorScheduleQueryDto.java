package com.azoker.pojo.dto;

import lombok.Data;

/**
 * Created by zxd on 2023/3/27
 */
@Data
public class DoctorScheduleQueryDto extends BasePageDto{

    private String date;

    private Long doctorId;

}
