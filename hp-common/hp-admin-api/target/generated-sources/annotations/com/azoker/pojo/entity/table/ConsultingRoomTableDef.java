package com.azoker.pojo.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

// Auto generate by mybatis-flex, do not modify it.
public class ConsultingRoomTableDef extends TableDef {

    /**
     * Created by zxd on 2023/7/6
     */
    public static final ConsultingRoomTableDef CONSULTING_ROOM = new ConsultingRoomTableDef();

    public final QueryColumn ID = new QueryColumn(this, "id");

    public final QueryColumn NAME = new QueryColumn(this, "name");

    public final QueryColumn DEP_ID = new QueryColumn(this, "dep_id");

    public final QueryColumn ADDRESS = new QueryColumn(this, "address");

    public final QueryColumn DELETED = new QueryColumn(this, "deleted");

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
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, NAME, DEP_ID, ADDRESS, CREATE_TIME, UPDATE_TIME, DESCRIPTION};

    public ConsultingRoomTableDef() {
        super("", "fr_consulting_room");
    }

}
