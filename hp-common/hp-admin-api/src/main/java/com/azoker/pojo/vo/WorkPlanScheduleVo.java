package com.azoker.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxd on 2023/4/1
 */
@Data
public class WorkPlanScheduleVo implements Serializable {


    private Long workPlanId;

    private Long doctorId;

    private String doctorName;

    //出诊时间段
    private Integer slot;

    //时段最大挂号上限
    private Integer maxNum;

    //上午
    private String forenoons;

    //下午
    private String afternoon;

}
