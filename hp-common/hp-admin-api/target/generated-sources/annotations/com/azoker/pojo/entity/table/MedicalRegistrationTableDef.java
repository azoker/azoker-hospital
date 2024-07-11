package com.azoker.pojo.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

// Auto generate by mybatis-flex, do not modify it.
public class MedicalRegistrationTableDef extends TableDef {

    /**
     * Created by zxd on 2023/7/6
     */
    public static final MedicalRegistrationTableDef MEDICAL_REGISTRATION = new MedicalRegistrationTableDef();

    public final QueryColumn ID = new QueryColumn(this, "id");

    public final QueryColumn DATE = new QueryColumn(this, "date");

    public final QueryColumn SLOT = new QueryColumn(this, "slot");

    public final QueryColumn CON_ID = new QueryColumn(this, "con_id");

    public final QueryColumn PAY_SN = new QueryColumn(this, "pay_sn");

    public final QueryColumn AMOUNT = new QueryColumn(this, "amount");

    public final QueryColumn PAY_TYPE = new QueryColumn(this, "pay_type");

    public final QueryColumn DOCTOR_ID = new QueryColumn(this, "doctor_id");

    public final QueryColumn PREPAY_ID = new QueryColumn(this, "prepay_id");

    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    public final QueryColumn OUT_TRADE_NO = new QueryColumn(this, "out_trade_no");

    public final QueryColumn WORK_PLAN_ID = new QueryColumn(this, "work_plan_id");

    public final QueryColumn PATIENT_CARD_ID = new QueryColumn(this, "patient_card_id");

    public final QueryColumn PAYMENT_STATUS = new QueryColumn(this, "payment_status");

    public final QueryColumn TRANSACTION_ID = new QueryColumn(this, "transaction_id");

    public final QueryColumn DOCTOR_SCHEDULE_ID = new QueryColumn(this, "doctor_schedule_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, DATE, SLOT, CON_ID, PAY_SN, AMOUNT, PAY_TYPE, DOCTOR_ID, PREPAY_ID, CREATE_TIME, OUT_TRADE_NO, WORK_PLAN_ID, PATIENT_CARD_ID, PAYMENT_STATUS, TRANSACTION_ID, DOCTOR_SCHEDULE_ID};

    public MedicalRegistrationTableDef() {
        super("", "fr_medical_registration");
    }

}
