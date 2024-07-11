package com.azoker.pojo.dto;

import io.swagger.models.parameters.SerializableParameter;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by zxd on 2023/7/5
 */
@Data
public class BasePageDto implements Serializable {

    //当前页数
    private Integer page=1;

    //分页大小
    private Integer limit=5;


}
