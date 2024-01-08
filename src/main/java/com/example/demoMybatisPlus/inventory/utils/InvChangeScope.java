package com.example.demoMybatisPlus.inventory.utils;

import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 库存-变更范围
 *
 * @author keno
 */
public enum InvChangeScope {
    /**
     * 全部
     */
    ALL("10", "全部"),
    /**
     * 汇总表
     */
    SUM("20", "汇总表"),
    /**
     * 明细表
     */
    DETAIL("30", "明细表"),

    FAC("40", "工厂库存表");

    private final String code;
    private final String info;

    InvChangeScope(String code, String info) {
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

    public boolean eq(InvChangeScope reconType) {
        if (reconType == null) {
            return false;
        }
        return reconType.getCode().equals(this.code);
    }

    public boolean included(List<String> scopes) {
        if (CollectionUtils.isEmpty(scopes)) {
            return false;
        }
        return scopes.contains(this.code);
    }

    public static boolean isDetail(List<String> scopes) {
        if (CollectionUtils.isEmpty(scopes)) {
            return false;
        }
        List<String> details = Arrays.asList(ALL.getCode(), DETAIL.getCode());
        for (String code : scopes) {
            return details.contains(code);
        }
        return false;
    }

    public static InvChangeScope ofCode(String code) {
        return resolve(code);
    }

    public static InvChangeScope ofInfo(String info) {
        InvChangeScope[] var1 = values();

        for (InvChangeScope eum : var1) {
            if (eum.info.equals(info)) {
                return eum;
            }
        }
        return null;
    }

    @Nullable
    public static InvChangeScope resolve(String code) {
        InvChangeScope[] var1 = values();

        for (InvChangeScope eum : var1) {
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
