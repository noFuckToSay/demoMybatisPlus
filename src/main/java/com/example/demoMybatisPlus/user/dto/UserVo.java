package com.example.demoMybatisPlus.user.dto;

import com.example.demoMybatisPlus.user.enums.UserStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author:22024002
 * @date:2023/5/19 9:06
 * @description:
 */
@Data
@ApiModel(value = "查询返回",description = "查询返回")
public class UserVo {

    @ApiModelProperty(value = "id",example = "123")
    private Long id;

    @ApiModelProperty(value = "姓名",example = "张三")
    private String name;

    @ApiModelProperty(value = "年龄",example = "20")
    private Integer age;

    @ApiModelProperty(value = "邮箱",example = "123@123.com")
    private String email;

    @ApiModelProperty(value = "创建时间",example = "2023-05-19 09:39:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;


    @ApiModelProperty(value = "修改时间",example = "2023-05-19 09:39:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;


    @ApiModelProperty(value = "创建人",example = "张三")
    private String createBy;


    @ApiModelProperty(value = "修改人",example = "李四")
    private String updateBy;

    @ApiModelProperty(value = "状态 枚举",example = "10")
    private UserStatusEnum status;
}
