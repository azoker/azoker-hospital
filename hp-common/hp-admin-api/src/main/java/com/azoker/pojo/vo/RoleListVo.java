package com.azoker.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxd on 2023/7/11
 */
@Data
public class RoleListVo implements Serializable {

    private Long id;

    private String name;

    private String pname;

    private String description;

    private Date createTime;

}
