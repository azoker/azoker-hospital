package com.azoker.pojo.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

// Auto generate by mybatis-flex, do not modify it.
public class RolePermissionTableDef extends TableDef {

    /**
     * Created by zxd on 2023/7/11
     */
    public static final RolePermissionTableDef ROLE_PERMISSION = new RolePermissionTableDef();

    public final QueryColumn ROLE_ID = new QueryColumn(this, "role_id");

    public final QueryColumn PERMISSION_ID = new QueryColumn(this, "permission_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ROLE_ID, PERMISSION_ID};

    public RolePermissionTableDef() {
        super("", "fr_role_permission");
    }

}
