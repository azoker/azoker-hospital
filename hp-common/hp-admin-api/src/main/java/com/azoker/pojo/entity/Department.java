package com.azoker.pojo.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxd on 2023/7/6
 */
@Data
@Builder //建造者模式
@NoArgsConstructor
@AllArgsConstructor
@Table("fr_department")//指定entity的名字，如果数据库的名称和实体一致可以不用配置
public class Department implements Serializable {

    //主键
    @Id(keyType = KeyType.Auto)
    private Long id;

    //科室名称
    //如果属性名和字段名，不需要配置
    private String name;

    //科室类型  1:门诊  0:非门诊
    private Integer type;

    //科室描述
    private String description;

    //推荐级别  1：首页推荐  0:不推荐
    private Integer recommended;

    //创建时间
    @Column("create_time")
    private Date createTime;

    //修改时间
    @Column("update_time")
    private Date updateTime;

    //删除标记(1:不可用 0:可用)
    @Column(isLogicDelete = true)
    private Integer deleted;


}
