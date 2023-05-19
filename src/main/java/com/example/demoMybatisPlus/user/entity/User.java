package com.example.demoMybatisPlus.user.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.example.demoMybatisPlus.user.enums.UserStatusEnum;
import lombok.Data;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *
 * </p>
 *
 * @author dingzk
 * @since 2023/04/17
 */
@Slf4j
@Data
@TableName(value = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * id 主键
     * */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 姓名
     * */
    private String name;

    /**
     * 年龄
     * */
    private Integer age;


    /**
     * 邮箱
     * */
    private String email;

    /**
     * 创建人
     * */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 创建时间
     * */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     * */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 修改人
     * */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 状态 枚举值
     * */
    private Integer status;

    @TableLogic
    private Integer isDel;
}
