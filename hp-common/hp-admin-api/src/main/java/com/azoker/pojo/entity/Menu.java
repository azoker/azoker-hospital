package com.azoker.pojo.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.activerecord.Model;
import lombok.Data;

import java.util.Date;
import java.util.List;


/**
 * Created by zxd on 2022/8/2
 */
@Table("fr_menu")
@Data
public class Menu extends Model<Menu> {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单权限标识
     */
    private String perms;

    /**
     * 前端path / 即跳转路由
     */
    private String path;

    /**
     * 菜单组件
     */
    private String component;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否为外链
     */
    private Boolean isFrame;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 菜单类型 （类型   0：目录   1：菜单   2：按钮）
     */
    private Integer type;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除标记(0--正常 1--删除)
     */
    @Column(isLogicDelete = true)
    private String delFlag;


    /**
     * 非数据库字段
     * 父菜单名称
     */
    @Column(ignore = true)
    private String parentName;

    /**
     * 非数据库字段
     * 菜单等级
     */
    @Column(ignore = true)
    private Integer level;

    /**
     * 非数据库字段
     * 子菜单
     */
    @Column(ignore = true)
    private List<Menu> children;



}
