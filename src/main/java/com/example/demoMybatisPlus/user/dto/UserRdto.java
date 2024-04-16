package com.example.demoMybatisPlus.user.dto;

import com.example.demoMybatisPlus.user.enums.UserStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author:22024002
 * @date:2023/5/19 9:06
 * @description:
 */
@Data
@ApiModel(value = "查询用户",description = "查询用户")
public class UserRdto {

    @ApiModelProperty(value = "用户名",example = "张三")
    private String userName;

    @ApiModelProperty(value = "密码",example = "12345.abc")
    private String password;

    @ApiModelProperty(value = "当前页数",example = "1")
    private Integer pageNum;
    @ApiModelProperty(value = "分页大小",example = "10")
    private Integer pageSize;
}
