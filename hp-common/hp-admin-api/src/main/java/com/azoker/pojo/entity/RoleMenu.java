package com.azoker.pojo.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 角色菜单表
 * Created by zxd on 2022/8/2
 */
@Table(value ="fr_role_menu")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleMenu implements Serializable {
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

    @Column(ignore = true)
    private static final long serialVersionUID = 1L;


}