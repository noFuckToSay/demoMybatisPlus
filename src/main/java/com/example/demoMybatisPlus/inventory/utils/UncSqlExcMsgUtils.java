package com.example.demoMybatisPlus.inventory.utils;

import cn.hutool.core.util.StrUtil;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

public class UncSqlExcMsgUtils {
    private static final Map<String, String> MSG_MAP = new HashMap<>(10);

    static {
        // 库存明细表约束提示
        MSG_MAP.put("wms_inv_detail_qty_check", "库存明细:总库存必须大于等于可用库存+冻结库存+锁定库存");
        MSG_MAP.put("wms_inv_detail_total_qty_gt_zero", "库存明细:总库存扣减不能为空");
        MSG_MAP.put("wms_inv_detail_avail_qty_gt_zero", "库存明细:可用库存扣减不能为空");
        MSG_MAP.put("wms_inv_detail_freeze_qty_gt_zero", "库存明细:冻结库存扣减不能为空");
        MSG_MAP.put("wms_inv_detail_locked_qty_gt_zero", "库存明细:锁定库存扣减不能为空");
        // 库存汇总表约束提示
        MSG_MAP.put("wms_inv_sum_qty_check", "库存汇总:总库存必须大于等于可用库存+冻结库存+锁定库存");
        MSG_MAP.put("wms_inv_sum_total_qty_gt_zero", "库存汇总:总库存扣减不能为空");
        MSG_MAP.put("wms_inv_sum_avail_qty_gt_zero", "库存汇总:可用库存扣减不能为空");
        MSG_MAP.put("wms_inv_sum_freeze_qty_gt_zero", "库存汇总:冻结库存扣减不能为空");
        MSG_MAP.put("wms_inv_sum_locked_qty_gt_zero", "库存汇总:锁定库存扣减不能为空");
    }

    public static String getMsgByExc(String exc) {
        if (StrUtil.isEmpty(exc)) {
            return "未知约束异常信息为空";
        }
        String msg = getMsgByKey(exc);
        if (StrUtil.isEmpty(msg)) {
            return exc;
        }
        return msg;
    }

    public static String getMsgByExc(UncategorizedSQLException use) {
        if (ObjectUtils.isEmpty(use) || ObjectUtils.isEmpty(use.getCause())) {
            return "未知约束异常信息为空";
        }
        return getMsgByExc(use.getCause().getMessage());
    }

    private static String getMsgByKey(String key) {
        return MSG_MAP.keySet().stream().filter(key::contains).map(MSG_MAP::get).findAny().orElse("");
    }
}
