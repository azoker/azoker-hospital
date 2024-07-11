package com.azoker.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zxd on 2023/3/27
 */
@Data
public class DoctorVo implements Serializable {

    //医生id
    private Long id;

    //医生名称
    private String name;

    //照片
    private String photo;

    //职位
    private String job;

    //备注信息
    private String remark;

    //介绍
    private String introduce;

    //价格
    private BigDecimal price;

}
