package com.azoker.pojo.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

// Auto generate by mybatis-flex, do not modify it.
public class DoctorTableDef extends TableDef {

    /**
     * Created by zxd on 2023/7/6
     */
    public static final DoctorTableDef DOCTOR = new DoctorTableDef();

    public final QueryColumn ID = new QueryColumn(this, "id");

    public final QueryColumn JOB = new QueryColumn(this, "job");

    public final QueryColumn PID = new QueryColumn(this, "pid");

    public final QueryColumn SEX = new QueryColumn(this, "sex");

    public final QueryColumn TAG = new QueryColumn(this, "tag");

    public final QueryColumn TEL = new QueryColumn(this, "tel");

    public final QueryColumn NAME = new QueryColumn(this, "name");

    public final QueryColumn CON_ID = new QueryColumn(this, "con_id");

    public final QueryColumn DEP_ID = new QueryColumn(this, "dep_id");

    public final QueryColumn EMAIL = new QueryColumn(this, "email");

    public final QueryColumn PHOTO = new QueryColumn(this, "photo");

    public final QueryColumn PRICE = new QueryColumn(this, "price");

    public final QueryColumn DEGREE = new QueryColumn(this, "degree");

    public final QueryColumn REMARK = new QueryColumn(this, "remark");

    public final QueryColumn SCHOOL = new QueryColumn(this, "school");

    public final QueryColumn STATUS = new QueryColumn(this, "status");

    public final QueryColumn ADDRESS = new QueryColumn(this, "address");

    public final QueryColumn DELETED = new QueryColumn(this, "deleted");

    public final QueryColumn BIRTHDAY = new QueryColumn(this, "birthday");

    public final QueryColumn ENTRY_DATE = new QueryColumn(this, "entry_date");

    public final QueryColumn INTRODUCE = new QueryColumn(this, "introduce");

    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    public final QueryColumn RECOMMENDED = new QueryColumn(this, "recommended");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, JOB, PID, SEX, TAG, TEL, NAME, CON_ID, DEP_ID, EMAIL, PHOTO, PRICE, DEGREE, REMARK, SCHOOL, STATUS, ADDRESS, BIRTHDAY, ENTRY_DATE, INTRODUCE, CREATE_TIME, UPDATE_TIME, RECOMMENDED};

    public DoctorTableDef() {
        super("", "fr_doctor");
    }

}
