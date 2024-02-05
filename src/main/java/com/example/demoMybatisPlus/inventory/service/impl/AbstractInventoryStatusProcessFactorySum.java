package com.example.demoMybatisPlus.inventory.service.impl;

import com.example.demoMybatisPlus.inventory.service.InventoryStatusProcessService;
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
public abstract class AbstractInventoryStatusProcessFactorySum<S extends InventoryStatusProcessService<T>, T, W extends InventoryWater> extends AbstractInventoryStatusProcess<S, T, W> {

    /**
     * 预处理数据
     */
    @Override
    public void doPreProcessWaters() {
        this.inventoryWaters = inventoryWaters
                .stream()
                .filter(w->{
                    return  InvChangeScope.ALL.included(w.getInvChangeScopes())
                            || InvChangeScope.SUM.included(w.getInvChangeScopes())
                            || InvChangeScope.FAC.included(w.getInvChangeScopes());
                })
                .collect(Collectors.toList());
    }


    /**
     * 处理库存唯一标识
     */
    @Override
    public void setIdentifiers() {
        InvIdentifierUtils.setIdentifierSumsSap(inventoryWaters);
    }


    protected List<W> getAvailableWaters(List<W> waters) {
        List<String> availableInvLocsOfAvailableInv = Arrays.asList(WhLocType.INV_LOC.getCode(), "", null);
        return waters.stream().filter(w -> availableInvLocsOfAvailableInv.contains(w.getWhLocType())).collect(Collectors.toList());
    }
}
