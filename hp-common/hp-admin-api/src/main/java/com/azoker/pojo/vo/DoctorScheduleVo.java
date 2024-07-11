package com.azoker.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zxd on 2023/4/2
 */
@Data
public class DoctorScheduleVo implements Serializable {

    private Long workPlanId;

    private Long scheduleId;

    //出诊时间段
    private Integer slot;

    //时段最大挂号上限
    private Integer maxNum;


    private Integer num;

}
