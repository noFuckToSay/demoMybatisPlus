package com.example.demoMybatisPlus.inventory;

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
public abstract class AbsDetailIsp<S extends InventoryStatusProcessService<T>, T, W extends InventoryWater> extends AbsIsp<S, T, W> {

    /**
     * 批量处理新增的库存信息
     */
    @Override
    public void batchProcessSaveWater() {
        service.saveBatchByIdentifier(paramNotDb);
    }

    /**
     * 批量处理更新的库存信息
     */
    @Override
    public void batchProcessUpdateWater() {
        service.updateBatchByIdentifier(paramInDb);
    }

    /**
     * 获取存在的唯一标识
     *
     * @param identifiers 唯一标识集合
     * @return 已存在数据库的唯一标识
     */
    @Override
    public List<Long> getByIdentifiers(List<Long> identifiers) {
        return service.getByIdentifiers(identifiers);
    }


    /**
     * 处理库存唯一标识
     */
    @Override
    public void setIdentifiers() {
        InvIdentifierUtils.setIdentifierWaters(waters);
    }

    /**
     * 预处理数据
     */
    @Override
    public void doPreProcessWaters() {
        this.waters = waters.stream().filter(getEligibleWaters()).collect(Collectors.toList());
    }

    /**
     * 获取符合条件的库存流水集合
     *
     * @return 库存流水集合
     */
    @NotNull
    private Predicate<W> getEligibleWaters() {
        return w -> InvChangeScope.ALL.included(w.getInvChangeScopes()) || InvChangeScope.DETAIL.included(w.getInvChangeScopes());
    }
}
