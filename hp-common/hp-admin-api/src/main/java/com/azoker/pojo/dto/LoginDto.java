package com.azoker.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by zxd on 2023/7/10
 */
@Data
@ApiModel("登录dto")
public class LoginDto implements Serializable {

    @ApiModelProperty("用户名")
    @Pattern(regexp = "^\\w{5,18}$",message = "用不名不合法")
    private String username;

    @ApiModelProperty("密码")
    @Pattern(regexp = "^\\w{6,20}$",message = "密码不合法")
    private String password;


}
