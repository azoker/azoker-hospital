package com.azoker.pojo.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zxd on 2023/7/6
 */
@Data
@Table("fr_medical_registration")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRegistration implements Serializable {

    //主键
    @Id(keyType = KeyType.Auto)
    private Long id;

    //就诊卡ID
    private Long patientCardId;

    //出诊计划ID
    private Long workPlanId;

    //出诊时段ID
    private Long doctorScheduleId;

    //医生ID
    private Long doctorId;

    //诊室ID
    private Long conId;

    //日期
    private Date date;

    //出诊时段编号
    private Integer slot;

    //挂号费金额
    private BigDecimal amount;

    //1.支付宝  2.微信
    private Integer payType;

    //付款状态: 1.未付款，2.已付款，3.已退款，4.已过期
    private Integer paymentStatus;

    //挂号单流水号（UUID）
    private String outTradeNo;

    //微信付款单预支付ID
    private String prepayId;

    //微信付款单支付ID
    private String transactionId;

    //创建日期
    private Date createTime;

    //支付流水号
    private String paySn;

}
