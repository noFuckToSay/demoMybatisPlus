package com.example.demoMybatisPlus.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author:22024002
 * @date:2023/5/19 9:06
 * @description:
 */
@Data
@ApiModel(value = "修改用户",description = "修改用户")
public class UserUdto {

    @ApiModelProperty(value = "id",example = "123")
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "用户名",example = "张三")
    @NotEmpty(message = "用户名")
    private String userName;

    @ApiModelProperty(value = "密码",example = "12345.abc")
    @NotNull(message = "密码不能为空")
    private String password;
}
