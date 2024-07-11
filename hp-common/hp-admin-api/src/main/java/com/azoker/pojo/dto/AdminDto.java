package com.azoker.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by zxd on 2023/7/10
 */
@Data
@ApiModel("管理员dto")
public class AdminDto implements Serializable {


    @ApiModelProperty("用户名")
    @Pattern(regexp = "^\\w{5,18}$",message = "用不名不合法")
    private String username;

    @ApiModelProperty("手机号码")
    @Pattern(regexp = "^((13[0-9])|(19[1-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$",message = "手机号码不合法")
    private String phone;

    @ApiModelProperty("邮箱")
    @Email(message = "邮箱不能为空")
    private String email;

    @ApiModelProperty("姓名")
    @NotBlank(message = "姓名不能为空")
    private String name;

    @ApiModelProperty("头像地址")
    @NotBlank(message = "头像地址不能为空")
    private String imageUrl;

    @ApiModelProperty("密码")
    @Pattern(regexp = "^\\w{6,20}$",message = "密码不合法")
    private String password;

    @ApiModelProperty("确认密码")
    @Pattern(regexp = "^\\w{6,20}$",message = "确认密码不合法")
    private String password2;


}
