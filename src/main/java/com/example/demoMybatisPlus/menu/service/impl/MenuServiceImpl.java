package com.example.demoMybatisPlus.menu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demoMybatisPlus.menu.entity.Menu;
import com.example.demoMybatisPlus.menu.mapper.MenuMapper;
import com.example.demoMybatisPlus.menu.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author dingzk
 * @since 2024/05/06
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<Menu> getMenus() {
        return this.getBaseMapper().getMenus();
    }
}
