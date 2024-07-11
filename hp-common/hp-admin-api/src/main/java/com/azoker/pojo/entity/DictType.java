package com.azoker.pojo.entity;


import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxd on 2023/3/26
 */
@Data
@Table("fr_dict_type")
@ApiModel("字典类型")
public class DictType implements Serializable {

    @Id(keyType = KeyType.Auto)
    @ApiModelProperty("主键")
    private Long id;

    //字典类型名称
    @ApiModelProperty("字典类型名称")
    private String name;

    //字典编号
    @ApiModelProperty("字典类型编号")
    private String code;

    //是否删除
    @Column(isLogicDelete = true)
    private Integer deleted;
        
    private Date createTime;
        
    private Date updateTime;


}

