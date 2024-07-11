package com.azoker.pojo.dto;


import lombok.Data;

import java.io.Serializable;

/**
 * Created by zxd on 2022/8/3
 */
@Data
public class RoutersMenuMetaDto implements Serializable {

    private String title;
    private String icon;

    public RoutersMenuMetaDto() {
    }

    public RoutersMenuMetaDto(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }

}
