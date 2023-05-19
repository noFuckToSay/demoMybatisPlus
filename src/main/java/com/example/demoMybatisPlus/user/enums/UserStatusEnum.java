package com.example.demoMybatisPlus.user.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @author:22024002
 * @date:2023/5/19 15:09
 * @description:
 */

public enum UserStatusEnum {
    NEW(10,"新建"),
    APPROVAL(20,"已审核");

    UserStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Getter
    @EnumValue
    @JsonValue
    private Integer code;
    @Getter
    private String name;
}
