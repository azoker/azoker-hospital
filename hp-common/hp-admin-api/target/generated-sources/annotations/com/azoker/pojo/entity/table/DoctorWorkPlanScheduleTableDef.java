package com.azoker.pojo.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

// Auto generate by mybatis-flex, do not modify it.
public class DoctorWorkPlanScheduleTableDef extends TableDef {

    /**
     * Created by zxd on 2023/7/6
     */
    public static final DoctorWorkPlanScheduleTableDef DOCTOR_WORK_PLAN_SCHEDULE = new DoctorWorkPlanScheduleTableDef();

    public final QueryColumn ID = new QueryColumn(this, "id");

    public final QueryColumn NUM = new QueryColumn(this, "num");

    public final QueryColumn SLOT = new QueryColumn(this, "slot");

    public final QueryColumn MAX_NUM = new QueryColumn(this, "max_num");

    public final QueryColumn WORK_PLAN_ID = new QueryColumn(this, "work_plan_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, NUM, SLOT, MAX_NUM, WORK_PLAN_ID};

    public DoctorWorkPlanScheduleTableDef() {
        super("", "fr_doctor_work_plan_schedule");
    }

}
