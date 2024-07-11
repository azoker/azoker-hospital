package com.azoker.pojo.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxd on 2023/7/6
 */
@Data
@Table("fr_doctor_work_plan")
public class DoctorWorkPlan implements Serializable {

    @Id(keyType = KeyType.Auto)
    private Long id;

    //医生id
    private Long doctorId;

    //诊室id
    private Long conId;

    //出诊日期
    private Date date;

    //当天挂号上限
    private Integer maxNum;

    //当天实际挂号
    private Integer num;

    //操作人id
    private Long adminId;

    //创建时间
    private Date createTime;

    //修改时间
    private Date updateTime;
}
