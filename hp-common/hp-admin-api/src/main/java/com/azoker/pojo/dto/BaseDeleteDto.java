package com.azoker.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zxd on 2023/7/6
 */
@Data
public class BaseDeleteDto implements Serializable {

    /**
     * 批量删除的id
     */
    private List<Long> ids;

}
