package com.azoker.pojo.dto;

import com.mybatisflex.annotation.Table;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxd on 2023/3/31
 */
@Data
@Table("fr_doctor_work_plan")
public class DoctorWorkPlanAddDto implements Serializable {

    //医生id
    @NotNull(message = "doctorId不能为空")
    private Long doctorId;

    //诊室id
    @NotNull(message = "conId不能为空")
    private Long conId;

    //出诊日期
    @NotNull(message = "date不能为空")
    private Date date;

    //出诊时段
    @NotEmpty(message = "slots不能为空")
    private Integer[] slots;

    //时段最大人数
    @NotNull(message = "slotMaxNum不能为空")
    private Integer slotMaxNum;

}
