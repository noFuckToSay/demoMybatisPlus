package com.example.demoMybatisPlus.menu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demoMybatisPlus.menu.entity.Menu;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author dingzk
 * @since 2024/05/06
 */
public interface MenuService extends IService<Menu> {


    List<Menu> getMenus();
}
