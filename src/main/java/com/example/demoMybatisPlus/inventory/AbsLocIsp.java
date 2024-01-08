package com.example.demoMybatisPlus.inventory;

import cn.hutool.core.util.HashUtil;
import com.example.demoMybatisPlus.inventory.entity.InventoryWater;
import com.example.demoMybatisPlus.inventory.utils.InvChangeScope;
import com.example.demoMybatisPlus.inventory.utils.InvChangeType;
import com.example.demoMybatisPlus.inventory.utils.InvIdentifierUtils;
import com.example.demoMybatisPlus.inventory.utils.InvType;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.*;

/**
 * 库存库位容量处理
 */
@Slf4j
public abstract class AbsLocIsp<S extends InventoryStatusProcessService<T>, T, W extends InventoryWater> extends AbsIsp<S, T, W> {


    @Override
    public void setIdentifiers() {
        InvIdentifierUtils.setIdentifierWhLocs(waters);
    }

    /**
     * 预处理数据
     */
    @Override
    public void doPreProcessWaters() {
        this.waters = waters.stream().filter(getEligibleWaters()).collect(Collectors.toList());
    }

    /**
     * 获取明细表 总库存操作的流水（库位容量计算只根据总库存计算）
     *
     * @return 库存流水集合
     */
    @NotNull
    private Predicate<W> getEligibleWaters() {
        return w -> InvChangeScope.isDetail(w.getInvChangeScopes()) && InvType.TOTAL.eq(w.getInvType());
    }


    /**
     * 处理流水生成库存信息
     */
    public void createSParamsFromWaters() {
        // 根据库位分组
        Map<Long, List<W>> watersPerIdentifier = waters.stream().collect(groupingBy(InventoryWater::getIdentifier));
        List<T> whLocs = new ArrayList<>();
        /*
         * 相同库位、相同标签下的逻辑处理
         */
        for (Long identifier : watersPerIdentifier.keySet()) {
            List<W> waterByIdentifier = watersPerIdentifier.get(identifier);
            List<W> watersWithoutDup = removingWatersDup(waterByIdentifier);
            // 同一个库位 同一个标签 增加的数量
            Long countInvLocForAdd = watersWithoutDup.stream().filter(w -> InvChangeType.ADD.eq(w.getChangeType())).count();
            // 同一个库位 同一个标签 减少的数量
            Long countInvLocForSub = getWatersForAllOut(watersWithoutDup).stream().filter(w -> InvChangeType.SUB.eq(w.getChangeType())).count();
            W water = watersPerIdentifier.get(identifier).get(0);
            T whLoc = toInvLocFromWater(water, countInvLocForAdd - countInvLocForSub);
            whLocs.add(whLoc);
        }
        this.paramInDb = whLocs;
    }

    /**
     * 获取全部出库流水
     *
     * @param ws 库存流水集合
     */
    public abstract List<W> getWatersForAllOut(List<W> ws);

    /**
     * 根据库位流水、库位容量
     *
     * @param water 库位流水
     * @param qty   库位容量
     * @return 库位容量对象
     */
    public abstract T toInvLocFromWater(W water, Long qty);

    /**
     * 去重复
     *
     * @param waters 库存流水集合
     * @return 库存流水集合
     */
    public List<W> removingWatersDup(List<W> waters) {
        return waters.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingLong(w -> HashUtil.mixHash(w.getIdentifier() + w.getLabelCode())))), ArrayList::new));
    }
}
