package com.azoker.pojo.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

// Auto generate by mybatis-flex, do not modify it.
public class MenuTableDef extends TableDef {

    /**
     * Created by zxd on 2022/8/2
     */
    public static final MenuTableDef MENU = new MenuTableDef();

    /**
     * 菜单ID
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 图标
     */
    public final QueryColumn ICON = new QueryColumn(this, "icon");

    /**
     * 菜单名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 前端path / 即跳转路由
     */
    public final QueryColumn PATH = new QueryColumn(this, "path");

    /**
     * 排序
     */
    public final QueryColumn SORT = new QueryColumn(this, "sort");

    /**
     * 菜单类型 （类型   0：目录   1：菜单   2：按钮）
     */
    public final QueryColumn TYPE = new QueryColumn(this, "type");

    /**
     * 菜单权限标识
     */
    public final QueryColumn PERMS = new QueryColumn(this, "perms");

    /**
     * 逻辑删除标记(0--正常 1--删除)
     */
    public final QueryColumn DEL_FLAG = new QueryColumn(this, "del_flag");

    /**
     * 是否为外链
     */
    public final QueryColumn IS_FRAME = new QueryColumn(this, "is_frame");

    /**
     * 父菜单ID
     */
    public final QueryColumn PARENT_ID = new QueryColumn(this, "parent_id");

    /**
     * 菜单组件
     */
    public final QueryColumn COMPONENT = new QueryColumn(this, "component");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 更新时间
     */
    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, ICON, NAME, PATH, SORT, TYPE, PERMS, IS_FRAME, PARENT_ID, COMPONENT, CREATE_TIME, UPDATE_TIME};

    public MenuTableDef() {
        super("", "fr_menu");
    }

}
