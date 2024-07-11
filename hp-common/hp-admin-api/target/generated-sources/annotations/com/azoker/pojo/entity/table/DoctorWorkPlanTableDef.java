package com.azoker.pojo.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

// Auto generate by mybatis-flex, do not modify it.
public class DoctorWorkPlanTableDef extends TableDef {

    /**
     * Created by zxd on 2023/7/6
     */
    public static final DoctorWorkPlanTableDef DOCTOR_WORK_PLAN = new DoctorWorkPlanTableDef();

    public final QueryColumn ID = new QueryColumn(this, "id");

    public final QueryColumn NUM = new QueryColumn(this, "num");

    public final QueryColumn DATE = new QueryColumn(this, "date");

    public final QueryColumn CON_ID = new QueryColumn(this, "con_id");

    public final QueryColumn MAX_NUM = new QueryColumn(this, "max_num");

    public final QueryColumn ADMIN_ID = new QueryColumn(this, "admin_id");

    public final QueryColumn DOCTOR_ID = new QueryColumn(this, "doctor_id");

    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, NUM, DATE, CON_ID, MAX_NUM, ADMIN_ID, DOCTOR_ID, CREATE_TIME, UPDATE_TIME};

    public DoctorWorkPlanTableDef() {
        super("", "fr_doctor_work_plan");
    }

}
