package com.example.demoMybatisPlus.inventory.impl;

import com.example.demoMybatisPlus.inventory.InventoryStatusProcessService;
import com.example.demoMybatisPlus.inventory.entity.InventoryWater;
import com.example.demoMybatisPlus.inventory.utils.InvChangeScope;
import com.example.demoMybatisPlus.inventory.utils.InvIdentifierUtils;
import com.example.demoMybatisPlus.inventory.utils.WhLocType;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/*
 * @author :22066104
 * @date  :2022/11/7 17:21
 * @description : sap库存总表处理
 */
public abstract class AbsFactorySumIsp <S extends InventoryStatusProcessService<T>, T, W extends InventoryWater> extends AbstractInventoryStatusProcess<S, T, W> {

    @Override
    public void batchProcessSaveWater() {
        inventoryStatusProcessService.saveBatchByIdentifier(paramNotDb);
    }

    /**
     * 批量处理更新的库存信息
     */
    @Override
    public void batchProcessUpdateWater() {
        inventoryStatusProcessService.updateBatchByIdentifier(paramInDb);
    }

    /**
     * 获取存在的唯一标识
     *
     * @param identifiers 唯一标识集合
     * @return 已存在数据库的唯一标识
     */
    @Override
    public List<Long> getByIdentifiers(List<Long> identifiers) {
        return inventoryStatusProcessService.getByIdentifiers(identifiers);
    }


    /**
     * 处理库存唯一标识
     */
    @Override
    public void setIdentifiers() {
        InvIdentifierUtils.setIdentifierSumsSap(inventoryWaters);
    }

    /**
     * 预处理数据
     */
    @Override
    public void doPreProcessWaters() {
        this.inventoryWaters = inventoryWaters.stream().filter(getEligibleWaters()).collect(Collectors.toList());
    }

    /**
     * 获取符合条件的库存流水集合
     *
     * @return 库存流水集合
     */
    @NotNull
    private Predicate<W> getEligibleWaters() {
        return w -> InvChangeScope.ALL.included(w.getInvChangeScopes()) || InvChangeScope.SUM.included(w.getInvChangeScopes())|| InvChangeScope.FAC.included(w.getInvChangeScopes());
    }

    protected List<W> getAvailableWaters(List<W> waters) {
        List<String> availableInvLocsOfAvailableInv = Arrays.asList(WhLocType.INV_LOC.getCode(), "", null);
        return waters.stream().filter(w -> availableInvLocsOfAvailableInv.contains(w.getWhLocType())).collect(Collectors.toList());
    }
}
