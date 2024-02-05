package com.example.demoMybatisPlus.inventory.service.impl;

import com.example.demoMybatisPlus.inventory.service.InventoryStatusProcessService;
import com.example.demoMybatisPlus.inventory.entity.InventoryWater;
import com.example.demoMybatisPlus.inventory.utils.InvChangeScope;
import com.example.demoMybatisPlus.inventory.utils.InvIdentifierUtils;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 库存明细处理
 *
 * @param <S> 服务
 * @param <T> 服务处理对象
 * @param <W> 库存流水
 */
public abstract class AbstractInventoryStatusProcessDetail<S extends InventoryStatusProcessService<T>, T, W extends InventoryWater> extends AbstractInventoryStatusProcess<S, T, W> {

    /**
     * 预处理数据
     */
    @Override
    public void doPreProcessWaters() {
        this.inventoryWaters = inventoryWaters
                .stream()
                .filter(w ->{
                    return  InvChangeScope.ALL.included(w.getInvChangeScopes())
                            || InvChangeScope.DETAIL.included(w.getInvChangeScopes());
                }).collect(Collectors.toList());
    }


    /**
     * 处理库存唯一标识
     */
    @Override
    public void setIdentifiers() {
        InvIdentifierUtils.setIdentifierWaters(inventoryWaters);
    }
}
