package com.azoker.pojo.dto.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

// Auto generate by mybatis-flex, do not modify it.
public class DoctorWorkPlanAddDtoTableDef extends TableDef {

    /**
     * Created by zxd on 2023/3/31
     */
    public static final DoctorWorkPlanAddDtoTableDef DOCTOR_WORK_PLAN_ADD_DTO = new DoctorWorkPlanAddDtoTableDef();

    public final QueryColumn DATE = new QueryColumn(this, "date");

    public final QueryColumn CON_ID = new QueryColumn(this, "con_id");

    public final QueryColumn SLOTS = new QueryColumn(this, "slots");

    public final QueryColumn DOCTOR_ID = new QueryColumn(this, "doctor_id");

    public final QueryColumn SLOT_MAX_NUM = new QueryColumn(this, "slot_max_num");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{DATE, CON_ID, SLOTS, DOCTOR_ID, SLOT_MAX_NUM};

    public DoctorWorkPlanAddDtoTableDef() {
        super("", "fr_doctor_work_plan");
    }

}
