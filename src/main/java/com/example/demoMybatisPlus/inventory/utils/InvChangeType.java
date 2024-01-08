package com.example.demoMybatisPlus.inventory.utils;

import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 库存-变动类型 字典 key
 *
 * @author keno
 */
public enum InvChangeType {
    /**
     * 增加 add
     */
    ADD("10", "增加"),
    /**
     * 减少 sub
     */
    SUB("20", "减少");

    private final String code;
    private final String info;

    InvChangeType(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public boolean eq(String code) {
        if (code == null) {
            return false;
        }
        return code.equals(this.code);
    }

    public boolean eq(InvChangeType reconType) {
        if (reconType == null) {
            return false;
        }
        return reconType.getCode().equals(this.code);
    }

    public static InvChangeType ofCode(String code) {
        return resolve(code);
    }

    public static InvChangeType ofInfo(String info) {
        InvChangeType[] var1 = values();

        for (InvChangeType eum : var1) {
            if (eum.info.equals(info)) {
                return eum;
            }
        }
        return null;
    }

    public static InvChangeType contraryOfCode(String code) {
        if (ADD.code.equals(code)) {
            return SUB;
        }
        if (SUB.code.equals(code)) {
            return ADD;
        }
        return null;
    }

    public static Integer setSign(String code, Integer value) {
        if (SUB.code.equals(code)) {
            return -Math.abs(value);
        }
        return Math.abs(value);
    }

    @Nullable
    public static InvChangeType resolve(String code) {
        InvChangeType[] var1 = values();

        for (InvChangeType eum : var1) {
            if (eum.code.equals(code)) {
                return eum;
            }
        }
        return null;
    }

    /**
     * 枚举编码集合
     *
     * @return 结果
     */
    public static List<String> codes() {
        return Arrays.stream(values()).map(x -> x.code).collect(Collectors.toList());
    }

    /**
     * 转字符串
     *
     * @return 结果
     */
    public static String toStr() {
        return Arrays.stream(values()).map(x -> String.format("%s:%s", x.code, x.info)).collect(Collectors.joining(","));
    }

    /**
     * 它返回枚举值的所有信息字符串的列表。
     *
     * @return 所有信息字符串的列表。
     */
    public static List<String> toInfos() {
        return Arrays.stream(values()).map(x -> x.info).collect(Collectors.toList());
    }
}
