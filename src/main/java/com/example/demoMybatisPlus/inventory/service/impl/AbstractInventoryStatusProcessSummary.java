package com.example.demoMybatisPlus.inventory.service.impl;

import com.example.demoMybatisPlus.inventory.entity.InventoryWater;
import com.example.demoMybatisPlus.inventory.service.InventoryStatusProcessService;
import com.example.demoMybatisPlus.inventory.utils.InvChangeScope;
import com.example.demoMybatisPlus.inventory.utils.InvIdentifierUtils;

import java.util.stream.Collectors;

/**
 * 库存汇总处理
 */
public abstract class AbstractInventoryStatusProcessSummary<S extends InventoryStatusProcessService<T>, T, W extends InventoryWater> extends AbstractInventoryStatusProcess<S, T, W> {



    /**
     * 预处理数据
     */
    @Override
    public void doPreProcessWaters() {
        this.inventoryWaters = inventoryWaters
                .stream()
                .filter(w->{
                    return InvChangeScope.ALL.included(w.getInvChangeScopes())
                        || InvChangeScope.SUM.included(w.getInvChangeScopes());
                })
                .collect(Collectors.toList());
    }

    /**
     * 处理库存唯一标识
     */
    @Override
    public void setIdentifiers() {
        InvIdentifierUtils.setIdentifierSums(inventoryWaters);
    }
}
