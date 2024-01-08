package com.example.demoMybatisPlus.inventory.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.example.demoMybatisPlus.inventory.service.InventoryStatsProcess;
import com.example.demoMybatisPlus.inventory.service.InventoryStatusProcessService;
import com.example.demoMybatisPlus.inventory.entity.InventoryWater;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * 库存统计处理抽象类
 *
 * @param <S> 服务（库存明细服务、库存汇总服务、库位容量服务）
 * @param <T> 服务处理对象
 * @param <W> 库存流水
 */
@Slf4j
public abstract class AbstractInventoryStatusProcess<S extends InventoryStatusProcessService<T>, T, W extends InventoryWater> implements InventoryStatsProcess<W> {

    protected S inventoryStatusProcessService;

    public void setService(S inventoryStatusProcessService) {
        this.inventoryStatusProcessService = inventoryStatusProcessService;
    }

    /**
     * 库存流水
     */
    protected List<W> inventoryWaters;

    /**
     * 不存在的库存信息
     */
    protected List<T> paramNotDb;

    /**
     * 已存在的库存信息
     */
    protected List<T> paramInDb;

    /**
     * 处理
     *
     * @param inventoryWaters 库存流水集合
     */
    @Override
    public void process(List<W> inventoryWaters) {
        try {
            doProcess(inventoryWaters);
        } catch (UncategorizedSQLException use) {
            log.error("库存操作违反约束", use);
            throw new RuntimeException(use.getMessage());
        }
    }

    /**
     * 处理步骤：
     * <p>
     * 1. 预处理库存流水
     * 2. 设置 Identifier 字段值
     * 3. 根据流水生成 S 服务处理（新增、更新操作）的对象集合
     * 4. S 服务批量新增
     * 5. S 服务批量更新
     *
     * @param inventoryWaters 库存流水集合
     */
    public void doProcess(List<W> inventoryWaters) {
        if (CollectionUtils.isEmpty(inventoryWaters)) {
            throw new RuntimeException("库存处理参数:库存流水为空。检查出库物料是否是本仓库的物料");
        }
        this.inventoryWaters = inventoryWaters;
        doPreProcessWaters();  //过滤获取符合条件的库存流水集合
        setIdentifiers();   //设置流水唯一标识
        createSParamsFromWaters();
        if (CollectionUtil.isNotEmpty(paramNotDb)) {
            batchProcessSaveWater();
        }
        if (CollectionUtil.isNotEmpty(paramInDb)) {
            batchProcessUpdateWater();
        }
    }

    /**
     * 预处理数据
     */
    public abstract void doPreProcessWaters();

    /**
     * 根据对象设置 identifier 字段值
     */
    public abstract void setIdentifiers();

    /**
     * 根据流水生成 S 服务处理（新增、更新操作）的对象集合
     * <p>
     * 主要处理流程：
     * 1. 根据新增、更新分组 库存流水信息集合
     * 2. 根据库存流水 汇总库存信息（库存的增减计算汇总）
     * 3. 批量新增库存信息（库存明细、库存汇总、库位容量）
     * 4. 批量更新库存信息（库存明细、库存汇总、库位容量）
     */
    public void createSParamsFromWaters() {
        List<Long> identifiers = inventoryWaters
                .stream()
                .map(W::getIdentifier)
                .collect(Collectors.toList());  //唯一标识list
        List<Long> identifiersInDb = getByIdentifiers(identifiers);   //已存在表中的唯一标识
        List<Long> identifiersNotDb = identifiers
                .stream()
                .filter(x -> !identifiersInDb.contains(x))
                .collect(Collectors.toList()); //在表中不存在的唯一标识

        // 根据唯一标识获取库存流水集合
        List<W> watersNotDb = inventoryWaters
                .stream()
                .filter(w -> identifiersNotDb.contains(w.getIdentifier()))
                .collect(Collectors.toList());  //需要新增到库存表中的流水
        // 根据唯一标识获取分组库存流水
        Map<Long, List<W>> watersPerIdentifierNotDb = watersNotDb
                .stream()
                .collect(groupingBy(W::getIdentifier));
        // 库存流水转库存记录
        this.paramNotDb = getSParamsFromWatersPerIdentifier(watersPerIdentifierNotDb);  //需要新增的记录，库存流水唯一标识的相同的汇总为一条库存记录

        List<W> watersInDb = inventoryWaters
                .stream()
                .filter(w -> identifiersInDb.contains(w.getIdentifier()))
                .collect(Collectors.toList());  //需要更新的库存的流水
        Map<Long, List<W>> watersPerIdentifierInDb = watersInDb
                .stream()
                .collect(groupingBy(W::getIdentifier));
        this.paramInDb = getSParamsFromWatersPerIdentifier(watersPerIdentifierInDb);  //需要更新的库存记录
    }

    /**
     * 获取存在的唯一标识
     *
     * @param identifiers 唯一标识集合
     * @return 已存在数据库的唯一标识
     */
    public abstract List<Long> getByIdentifiers(List<Long> identifiers);

    /**
     * 根据 Identifier 把库存流水转 S 服务处理的对象集合
     *
     * @param preWaters 库存流水
     * @return 库存明细集合
     */
    public List<T> getSParamsFromWatersPerIdentifier(Map<Long, List<W>> preWaters) {
        List<T> details = new ArrayList<>();
        for (Long id : preWaters.keySet()) {
            List<W> inventoryWaters = preWaters.get(id);
            details.add(getSParamsFromWaters(inventoryWaters));
        }
        return details;
    }

    /**
     * 根据库存流水生成 S 服务参数（库存明细、库存汇总、库位容量）
     *
     * @param inventoryWaters 库存流水集合
     * @return 库存明细
     */
    public abstract T getSParamsFromWaters(List<W> inventoryWaters);

    /**
     * 批量处理新增的库存信息（无新增数据不调用此方法）
     */
    public void batchProcessSaveWater() {
    }

    /**
     * 批量处理更新的库存信息（无更新数据不调用此方法）
     */
    public abstract void batchProcessUpdateWater();
}
