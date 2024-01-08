package com.example.demoMybatisPlus.inventory.utils;

import java.util.Arrays;
import java.util.List;

/**
 * 库存唯一标识工具类
 */
public class InvIdentifierUtils {

    /**
     * 库位容量 生成唯一标识字段 ，由 仓库编码 + 库区编码 + 库位编码 组成
     */
    private static final List<String> INV_LOC_FIELDS = Arrays.asList("whCode", "whAreaCode", "whLocCode", "whLocType");

    /**
     * 库存详情 生成唯一标识字段 ，由 需求事业部编码 + 入库批次号 + 仓库编码 + 库区编码 + 库位编码 + 托盘号 + 标签号 + 物料编码 + 物料属性 组成
     */
    private static final List<String> INV_DETAIL_FIELDS = Arrays.asList("demandDivisionCode", "inBatchNo", "whCode", "whAreaCode", "whLocCode", "whLocType", "palletCode", "labelCode", "materialCode", "materialProp");
    /**
     * 库存流水 生成唯一标识字段 ，由 需求事业部编码 + 入库批次号 + 仓库编码 + 库区编码 + 库位编码 + 托盘号 + 标签号 + 物料编码 + 物料属性 组成
     */
    private static final List<String> INV_WATER_FIELDS = Arrays.asList("demandDivisionCode", "inBatchNo", "whCode", "whAreaCode", "whLocCode", "whLocType", "palletCode", "labelCode", "materialCode", "materialProp");
    /**
     * 库存汇总 生成唯一标识字段 ，由 客户编码 + 仓库编码 + 库位编码 + 物料属性 组成
     */
    private static final List<String> INV_SUM_FIELDS = Arrays.asList("cusCode", "whCode", "materialCode", "materialProp");

    /*
     *sap库存汇总唯一标识， 客户编码 + 仓库编码 + 库位编码 + 物料属性 + 工厂编码 + 是否是sap库存（0，1）
     **/
    private static final List<String> INV_SUM_FIELDS_SAP = Arrays.asList("cusCode", "whCode", "materialCode", "materialProp","sureDeptCode","isSap");

    private static final String IDENTIFIER_FIELD = "identifier";

    /**
     * 流水规则字段
     */
    public final static List<String> WATER_RULES_FIELDS = Arrays.asList("orderType", "orderStatus", "materialLabelStatus", "materialRowStatus", "whLocType");

    /**
     * 批量设置 库存详情 唯一标识
     *
     * @param rows 对象集合
     * @param <T>  对象类型
     */
    public static <T> void setIdentifierDetails(List<T> rows) {
        if (CollUtils.isEmpty(rows)) {
            return;
        }
        CollUtils.convertObjectToList(rows).forEach(InvIdentifierUtils::setIdentifierDetail);
    }

    /**
     * 设置 库存详情 唯一标识
     *
     * @param row 对象
     */
    public static <T> void setIdentifierDetail(T row) {
        if (ObjectUtils.isNull(row)) {
            return;
        }
        ObjectUtils.setLong(row, IDENTIFIER_FIELD, identifierDetail(row));
    }

    /**
     * 库存详情 唯一标识生成
     *
     * @param row 对象
     * @return 唯一标识
     */
    public static <T> Long identifierDetail(T row) {
        if (ObjectUtils.isNull(row)) {
            return null;
        }
        return ObjectUtils.hash(row, INV_DETAIL_FIELDS);
    }

    /**
     * 批量设置 库存流水 唯一标识
     *
     * @param rows 对象集合
     */
    public static <T> void setIdentifierWaters(List<T> rows) {
        if (CollUtils.isEmpty(rows)) {
            return;
        }
        CollUtils.convertObjectToList(rows).forEach(InvIdentifierUtils::setIdentifierWater);
    }

    /**
     * 设置 库存流水 唯一标识
     *
     * @param row 对象
     */
    public static <T> void setIdentifierWater(T row) {
        if (ObjectUtils.isNull(row)) {
            return;
        }
        ObjectUtils.setLong(row, IDENTIFIER_FIELD, IdentifierWater(row));
    }

    /**
     * 库存流水 唯一标识生成
     *
     * @param row 对象
     * @return 唯一标识
     */
    public static <T> Long IdentifierWater(T row) {
        if (ObjectUtils.isNull(row)) {
            return null;
        }
        return ObjectUtils.hash(row, INV_WATER_FIELDS);
    }

    /**
     * 批量设置 库存汇总 唯一标识
     *
     * @param rows 对象集合
     */
    public static <T> void setIdentifierSums(List<T> rows) {
        if (CollUtils.isEmpty(rows)) {
            return;
        }
        CollUtils.convertObjectToList(rows).forEach(InvIdentifierUtils::setIdentifierSum);
    }

    /**
     * 批量设置 sap库存汇总 唯一标识
     *
     * @param rows 对象集合
     */
    public static <T> void setIdentifierSumsSap(List<T> rows) {
        if (CollUtils.isEmpty(rows)) {
            return;
        }
        CollUtils.convertObjectToList(rows).forEach(InvIdentifierUtils::setIdentifierSumSap);
    }

    /**
     * 设置 库存汇总 唯一标识
     *
     * @param row 对象
     */
    public static <T> void setIdentifierSum(T row) {
        if (ObjectUtils.isNull(row)) {
            return;
        }
        ObjectUtils.setLong(row, IDENTIFIER_FIELD, IdentifierSum(row));
    }

    /**
     * 设置 sap库存汇总 唯一标识
     *
     * @param row 对象
     */
    public static <T> void setIdentifierSumSap(T row) {
        if (ObjectUtils.isNull(row)) {
            return;
        }
        ObjectUtils.setLong(row, IDENTIFIER_FIELD, IdentifierSumSap(row));
    }

    /**
     * 库存汇总 唯一标识生成
     *
     * @param row 对象
     * @return 唯一标识
     */
    public static <T> Long IdentifierSum(T row) {
        if (ObjectUtils.isNull(row)) {
            return null;
        }
        return ObjectUtils.hash(row, INV_SUM_FIELDS);
    }

    /**
     * sap库存汇总 唯一标识生成
     * @param row 对象
     * @return 唯一标识
     */
    public static <T> Long IdentifierSumSap(T row) {
        if (ObjectUtils.isNull(row)) {
            return null;
        }
        return ObjectUtils.hash(row, INV_SUM_FIELDS_SAP);
    }

    /**
     * 批量设置 库存库位 唯一标识
     *
     * @param rows 对象集合
     */
    public static <T> void setIdentifierWhLocs(List<T> rows) {
        if (CollUtils.isEmpty(rows)) {
            return;
        }
        CollUtils.convertObjectToList(rows).forEach(InvIdentifierUtils::setIdentifierWhLoc);
    }

    /**
     * 设置 库存库位 唯一标识
     *
     * @param row 对象
     */
    public static <T> void setIdentifierWhLoc(T row) {
        if (ObjectUtils.isNull(row)) {
            return;
        }
        ObjectUtils.setLong(row, IDENTIFIER_FIELD, identifierWhLoc(row));
    }

    /**
     * 库存库位 唯一标识生成
     *
     * @param row 对象
     * @return 唯一标识
     */
    public static <T> Long identifierWhLoc(T row) {
        if (ObjectUtils.isNull(row)) {
            return null;
        }
        return ObjectUtils.hash(row, INV_LOC_FIELDS);
    }

    /**
     * 批量设置 库存计算规则 唯一标识
     *
     * @param rows 对象集合
     */
    public static <T> void setIdentifierInvRules(List<T> rows) {
        if (CollUtils.isEmpty(rows)) {
            return;
        }
        CollUtils.convertObjectToList(rows).forEach(InvIdentifierUtils::setIdentifierInvRule);
    }

    /**
     * 设置 库存计算规则 唯一标识
     *
     * @param row 对象
     */
    public static <T> void setIdentifierInvRule(T row) {
        if (ObjectUtils.isNull(row)) {
            return;
        }
        ObjectUtils.setLong(row, IDENTIFIER_FIELD, identifierInvRule(row));
    }

    /**
     * 库存计算规则 唯一标识生成
     *
     * @param row 对象
     * @return 唯一标识
     */
    public static <T> Long identifierInvRule(T row) {
        if (ObjectUtils.isNull(row)) {
            return null;
        }
        return ObjectUtils.hash(row, WATER_RULES_FIELDS);
    }
}
