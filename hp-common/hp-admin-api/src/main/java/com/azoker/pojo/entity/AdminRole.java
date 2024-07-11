package com.azoker.pojo.entity;

import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by zxd on 2023/7/11
 */
@Data
@Table("fr_admin_role")
public class AdminRole implements Serializable {

    private Long adminId;

    private Long roleId;

}
