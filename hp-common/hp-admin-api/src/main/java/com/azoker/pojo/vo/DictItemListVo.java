package com.azoker.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zxd on 2023/3/26
 */
@Data
public class DictItemListVo implements Serializable {

    private Long id;

    //字典选项值
    private String name;

    //字典类型名称
    private String dictTypeName;
}
