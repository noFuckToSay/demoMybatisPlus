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
    @NotNull(message = "id不能为空}")
    private Long id;

    @ApiModelProperty(value = "姓名",example = "张三")
    @NotEmpty(message = "姓名不能为空}")
    private String name;

    @ApiModelProperty(value = "年龄",example = "20")
    @NotNull(message = "年龄不能为空}")
    private Integer age;

    @ApiModelProperty(value = "邮箱",example = "123@123.com")
    private String email;
}
