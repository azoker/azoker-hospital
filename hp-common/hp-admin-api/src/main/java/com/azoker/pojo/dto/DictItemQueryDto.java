package com.azoker.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zxd on 2023/7/6
 */
@Data
public class DictItemQueryDto extends BasePageDto implements Serializable {


    //字典选项名称模糊查询
    private String name;

    //字典类型id
    private Long dicTypeId;


}
