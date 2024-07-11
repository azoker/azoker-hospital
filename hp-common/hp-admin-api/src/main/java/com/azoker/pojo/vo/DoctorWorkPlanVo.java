package com.azoker.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxd on 2023/3/31
 */
@Data
public class DoctorWorkPlanVo implements Serializable {

    private String deptName;

    private Long conId;

    private Long workPlanId;

    private String conName;

    private String doctorName;

    private Date date;


}
