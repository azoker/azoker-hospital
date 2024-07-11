package com.azoker.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxd on 2023/7/7
 */
@Data
public class DoctorListVo implements Serializable {

    private Long id;

    //医生名称
    private String name;

    //性别
    private String sex;

    //电话
    private String tel;

    //职位
    private String jobName;

    //科室
    private String depName;

    //诊室
    private String conName;

    //入职日期
    private Date entryDate;

    //学校
    private String school;

    //学历
    private String degreeName;

    //状态
    private Integer status;


}
