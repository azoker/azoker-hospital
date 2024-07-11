package com.azoker.pojo.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxd on 2023/7/11
 */
@Data
@Table("fr_role")
public class Role implements Serializable {

    @Id(keyType = KeyType.Auto)
    private Long id;

    private String name;

    private String description;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;


}
