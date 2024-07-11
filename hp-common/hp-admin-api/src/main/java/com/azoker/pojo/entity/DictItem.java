package com.azoker.pojo.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxd on 2023/7/6
 */
@Data
@Table("fr_dict_item")
public class DictItem implements Serializable{

    @Id(keyType = KeyType.Auto)
    private Long id;
        
    private String name;
        
    private Long dicTypeId;

    @Column(isLogicDelete = true)
    private Integer deleted;
        
    private Date createTime;
        
    private Date updateTime;


}

