package com.azoker.pojo.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

// Auto generate by mybatis-flex, do not modify it.
public class RoleTableDef extends TableDef {

    /**
     * Created by zxd on 2023/7/11
     */
    public static final RoleTableDef ROLE = new RoleTableDef();

    public final QueryColumn ID = new QueryColumn(this, "id");

    public final QueryColumn NAME = new QueryColumn(this, "name");

    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    public final QueryColumn DESCRIPTION = new QueryColumn(this, "description");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, NAME, CREATE_TIME, UPDATE_TIME, DESCRIPTION};

    public RoleTableDef() {
        super("", "fr_role");
    }

}
