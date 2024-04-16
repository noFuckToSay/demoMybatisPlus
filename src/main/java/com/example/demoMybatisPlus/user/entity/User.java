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
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 姓名
     * */
    private String userName;

    /**
     * 姓名
     * */
    private String password;


    /**
     * 创建人
     * */
    private String createBy;

    /**
     * 创建时间
     * */
    private LocalDateTime createTime;

    /**
     * 修改时间
     * */
    private LocalDateTime updateTime;

    /**
     * 修改人
     * */
    private String updateBy;


    @TableLogic
    private Integer isDel;
}
