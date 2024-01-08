package com.example.demoMybatisPlus.inventory.utils;

import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 库存-类型 字典 key
 *
 * @author keno
 */
public enum InvType {
    /**
     * 总库存
     */
    TOTAL("10", "总库存"),
    /**
     * 可用库存
     */
    AVAILABLE("20", "可用库存"),
    /**
     * 冻结库存
     */
    FREEZE("30", "冻结库存"),
    /**
     * 锁定库存
     */
    LOCKED("40", "锁定库存");

    private final String code;
    private final String info;

    InvType(String code, String info) {
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

    public boolean eq(InvType reconType) {
        if (reconType == null) {
            return false;
        }
        return reconType.getCode().equals(this.code);
    }

    public static InvType ofCode(String code) {
        return resolve(code);
    }

    public static InvType ofInfo(String info) {
        InvType[] var1 = values();

        for (InvType eum : var1) {
            if (eum.info.equals(info)) {
                return eum;
            }
        }
        return null;
    }

    @Nullable
    public static InvType resolve(String code) {
        InvType[] var1 = values();

        for (InvType eum : var1) {
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
