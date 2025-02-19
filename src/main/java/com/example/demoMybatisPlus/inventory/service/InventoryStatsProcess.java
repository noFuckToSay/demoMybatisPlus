package com.example.demoMybatisPlus.inventory.service;

import com.example.demoMybatisPlus.inventory.entity.InventoryWater;

import java.util.List;

/**
 * 库存统计处理接口
 *
 * @param <W>
 */
public interface InventoryStatsProcess<W extends InventoryWater> {

    /**
     * 处理
     *
     * @param waters 库存流水集合
     */
    void process(List<W> inventoryWaters);
}
