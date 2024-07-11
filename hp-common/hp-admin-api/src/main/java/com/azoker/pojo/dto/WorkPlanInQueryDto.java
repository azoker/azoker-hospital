package com.azoker.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxd on 2023/4/1
 */
@Data
public class WorkPlanInQueryDto implements Serializable {

    //开始时间
    @NotBlank(message = "startDate不能为空")
    private String startDate;

    //结束时间
    private String endDate;

    //诊室id
    private Integer conId;

    //医生名字
    private String doctorName;


}
