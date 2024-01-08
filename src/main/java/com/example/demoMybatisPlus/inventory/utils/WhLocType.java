package com.example.demoMybatisPlus.inventory.utils;

import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 库存-库位类型 字典 key HE0045
 * <p>
 * temp_inv_loc = til
 * inv_loc = il
 *
 * @author keno
 */
public enum WhLocType {
    /**
     * 入库暂存库位
     */
    IN_TIL("10", "入库暂存库位"),
    /**
     * 出库暂存库位
     */
    OUT_TIL("20", "出库暂存库位"),
    /**
     * 存储库位
     */
    INV_LOC("30", "存储库位"),
    /**
     * 退货库位
     */
    RETURN_INV_LOC("40", "退货库位"),
    /**
     * 移库暂存区
     */
    MOVING_TIL("50", "移库暂存区"),
    /**
     * 冻结暂存区
     */
    FREEZE_TIL("60", "冻结暂存区"),
    /**
     * 不良品暂存区
     */
    BAD_TIL("70", "不良品暂存区");

    private final String code;
    private final String info;

    WhLocType(String code, String info) {
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

    public boolean eq(WhLocType reconType) {
        if (reconType == null) {
            return false;
        }
        return reconType.getCode().equals(this.code);
    }

    public static WhLocType ofCode(String code) {
        return resolve(code);
    }

    public static WhLocType ofInfo(String info) {
        WhLocType[] var1 = values();

        for (WhLocType eum : var1) {
            if (eum.info.equals(info)) {
                return eum;
            }
        }
        return null;
    }

    @Nullable
    public static WhLocType resolve(String code) {
        WhLocType[] var1 = values();

        for (WhLocType eum : var1) {
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
