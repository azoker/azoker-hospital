package com.azoker.pojo.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

// Auto generate by mybatis-flex, do not modify it.
public class RoleMenuTableDef extends TableDef {

    /**
     * 角色菜单表
 Created by zxd on 2022/8/2
     */
    public static final RoleMenuTableDef ROLE_MENU = new RoleMenuTableDef();

    /**
     * 菜单ID
     */
    public final QueryColumn MENU_ID = new QueryColumn(this, "menu_id");

    /**
     * 角色ID
     */
    public final QueryColumn ROLE_ID = new QueryColumn(this, "role_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{MENU_ID, ROLE_ID};

    public RoleMenuTableDef() {
        super("", "fr_role_menu");
    }

}
