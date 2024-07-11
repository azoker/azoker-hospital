package com.azoker.pojo.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zxd on 2023/7/6
 */
@Data
@Table("fr_doctor")
public class Doctor implements Serializable {

    @Id(keyType = KeyType.Auto)
    private Long id;
    //姓名
    private String name;
    //身份证号码
    private String pid;
    //性别
    private String sex;
    //挂号费用
    private BigDecimal price;
    //照片
    private String photo;
    //毕业学校
    private String school;
    //入职日期
    private Date entryDate;
    //电话
    private String tel;
    //家庭住址
    private String address;
    //电子邮箱
    private String email;
    //出生日期
    private Date birthday;
    //备注信息
    private String remark;
    //标签
    private String tag;
    //推荐等级  1:推荐  0:普通
    private Integer recommended;
    //状态  1:在职  2:离职  3:退休
    private Integer status;
    //介绍
    private String introduce;

    //科室Id
    private Long depId;

    //诊室id
    private Long conId;

    //学历(字典表id)
    private Long degree;

    //职务(字典表id)
    private Long job;

    //创建时间
    @Column("create_time")//如果属性名和表的字段名不一致，配置名称  如果一致不需要配置
    private Date createTime;

    //修改时间
    @Column("update_time")
    private Date updateTime;

    //删除标记(1:不可用 0:可用)
    @Column(isLogicDelete = true)
    private Integer deleted;

}