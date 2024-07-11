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
@Table("fr_admin")
public class Admin implements Serializable {

    //主键
    @Id(keyType = KeyType.Auto)
    private Long id;

    private String username;

    private String phone;

    private String email;

    private String name;

    private String imageUrl;

    private String password;

    private String salt;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

    //忽略该字段
    @Column(ignore = true)
    private String permissionUris;

    @Column(ignore = true)
    private String permissionTags;

}
