package com.azoker.pojo.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

// Auto generate by mybatis-flex, do not modify it.
public class DictItemTableDef extends TableDef {

    /**
     * Created by zxd on 2023/7/6
     */
    public static final DictItemTableDef DICT_ITEM = new DictItemTableDef();

    public final QueryColumn ID = new QueryColumn(this, "id");

    public final QueryColumn NAME = new QueryColumn(this, "name");

    public final QueryColumn DELETED = new QueryColumn(this, "deleted");

    public final QueryColumn DIC_TYPE_ID = new QueryColumn(this, "dic_type_id");

    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, NAME, DIC_TYPE_ID, CREATE_TIME, UPDATE_TIME};

    public DictItemTableDef() {
        super("", "fr_dict_item");
    }

}
