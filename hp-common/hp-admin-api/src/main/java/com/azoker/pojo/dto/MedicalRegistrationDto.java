package com.azoker.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zxd on 2023/4/22
 */
@Data
public class MedicalRegistrationDto implements Serializable {

    //出诊计划ID
    private Long workPlanId;

    //出诊时段ID
    private Long doctorScheduleId;

    //日期
    private Date date;



}
