package com.azoker.pojo.dto;

import lombok.Data;
import java.io.Serializable;

/**
 * Created by zxd on 2023/4/1
 */
@Data
public class WorkPlanScheduleQueryDto implements Serializable {

    //日期
    private String date;

    //诊室id
    private Integer conId;

}
