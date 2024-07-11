package com.azoker.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxd on 2023/3/30
 */
@Data
public class AdminListVo implements Serializable {

    private Long id;

    private String name;

    private String rname;

    private String phone;

    private String email;

    private String imageUrl;

    private Date createTime;

}
