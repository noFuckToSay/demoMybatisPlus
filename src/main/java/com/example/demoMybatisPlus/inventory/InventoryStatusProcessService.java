package com.example.demoMybatisPlus.inventory;

import java.util.List;

/**
 * 库存统计处理接口
 */
public interface InventoryStatusProcessService<T> {

    /**
     * 批量新增
     *
     * @param ts 参数集合
     * @return 行数
     */
    int saveBatchByIdentifier(List<T> inventorys);

    /**
     * 批量修改
     *
     * @param ts 参数集合
     * @return 行数
     */
    int updateBatchByIdentifier(List<T> inventorys);

    /**
     * 根据 identifier 查询是否存在存在数据库
     *
     * @param identifiers 唯一标识集合
     * @return 存在数据库的 identifier 集合
     */
    List<Long> getByIdentifiers(List<Long> identifiers);
}
