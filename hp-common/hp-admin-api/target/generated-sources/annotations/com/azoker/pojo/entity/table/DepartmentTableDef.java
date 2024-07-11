package com.azoker.pojo.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

// Auto generate by mybatis-flex, do not modify it.
public class DepartmentTableDef extends TableDef {

    /**
     * Created by zxd on 2023/7/6
     */
    public static final DepartmentTableDef DEPARTMENT = new DepartmentTableDef();

    public final QueryColumn ID = new QueryColumn(this, "id");

    public final QueryColumn NAME = new QueryColumn(this, "name");

    public final QueryColumn TYPE = new QueryColumn(this, "type");

    public final QueryColumn DELETED = new QueryColumn(this, "deleted");

    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    public final QueryColumn DESCRIPTION = new QueryColumn(this, "description");

    public final QueryColumn RECOMMENDED = new QueryColumn(this, "recommended");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, NAME, TYPE, CREATE_TIME, UPDATE_TIME, DESCRIPTION, RECOMMENDED};

    public DepartmentTableDef() {
        super("", "fr_department");
    }

}
