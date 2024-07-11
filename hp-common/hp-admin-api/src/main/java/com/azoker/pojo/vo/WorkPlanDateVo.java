package com.azoker.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by zxd on 2023/4/1
 */
@Data
public class WorkPlanDateVo implements Serializable {

    //医生id
    private Long id;

    //医生姓名
    private String name;

    //医生照片
    private String photo;

    //医生职务
    private String job;

    //医生介绍
    private String introduce;

    //已经挂号数量
    private Integer num;

    //当日挂号上限
    private Integer maxNum;

    //挂号费用
    private BigDecimal price;

}
