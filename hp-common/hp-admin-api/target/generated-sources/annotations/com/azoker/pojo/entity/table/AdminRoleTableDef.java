package com.azoker.pojo.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

// Auto generate by mybatis-flex, do not modify it.
public class AdminRoleTableDef extends TableDef {

    /**
     * Created by zxd on 2023/7/11
     */
    public static final AdminRoleTableDef ADMIN_ROLE = new AdminRoleTableDef();

    public final QueryColumn ROLE_ID = new QueryColumn(this, "role_id");

    public final QueryColumn ADMIN_ID = new QueryColumn(this, "admin_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ROLE_ID, ADMIN_ID};

    public AdminRoleTableDef() {
        super("", "fr_admin_role");
    }

}
