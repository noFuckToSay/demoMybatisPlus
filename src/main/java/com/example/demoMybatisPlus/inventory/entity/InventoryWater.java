package com.example.demoMybatisPlus.inventory.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class InventoryWater implements java.io.Serializable {
    /**
     * 版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 标签号
     */
    @ApiModelProperty(value = "标签号")
    private String labelCode;

    /**
     * 库位类型（参考：枚举HE0045）
     */
    @ApiModelProperty(value = "库位类型（参考：枚举HE0045）")
    private String whLocType;

    /**
     * 唯一标识
     */
    @ApiModelProperty(value = "唯一标识")
    private Long identifier;

    /**
     * 库存类型(10.总库存，20.可用库存，30.冻结库存，40.锁定库存)
     */
    @ApiModelProperty(value = "库存类型(10.总库存，20.可用库存，30.冻结库存，40.锁定库存)")
    private String invType;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal qty;

    /**
     * 变动类型(10.增加，20.减少)
     */
    @ApiModelProperty(value = "变动类型(10.增加，20.减少)")
    private String changeType;

    /**
     * 变更范围（为空全部更新 10.全部 20.汇总表 30.明细表）
     */
    @ApiModelProperty(value = "变更范围（为空全部更新 10.全部 20.汇总表 30.明细表）")
    protected List<String> invChangeScopes;
}
