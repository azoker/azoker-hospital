package com.azoker.pojo.entity;

/**
 * Created by zxd on 2023/7/5
 */
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxd on 2023/7/6
 */
@Data
@Table("fr_consulting_room")
public class ConsultingRoom implements Serializable {

    //主键
    @Id(keyType = KeyType.Auto)
    private Long id;

    //诊室名称
    private String name;

    //科室id
    @Column("dep_id")
    private Long depId;

    //诊室地址
    private String address;

    //诊室描述
    private String description;

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
