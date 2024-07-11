package com.azoker.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxd on 2023/7/5
 */
@Data
public class ConsultingRoomListVo implements Serializable {

    private Long id;

    private String name;

    private String depName;

    private String address;

    private Date createTime;

}
