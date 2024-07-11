package com.azoker.pojo.entity;


import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by zxd on 2023/7/6
 */
@Data
@Table("fr_doctor_work_plan_schedule")
public class DoctorWorkPlanSchedule implements Serializable {

    @Id(keyType = KeyType.Auto)
    private Long id;

    //医生出诊id
    private Long workPlanId;

    //出诊时间段(用数字代表对应时间段)
    private Integer slot;

    //该时段挂号上限
    private Integer maxNum;

    //该时段实际挂号人数
    private Integer num;
}
