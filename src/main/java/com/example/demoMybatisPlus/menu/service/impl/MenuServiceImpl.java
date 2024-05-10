package com.example.demoMybatisPlus.menu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demoMybatisPlus.menu.entity.Menu;
import com.example.demoMybatisPlus.menu.mapper.MenuMapper;
import com.example.demoMybatisPlus.menu.service.MenuService;
import com.example.demoMybatisPlus.menu.vo.MenuTreeVo;
import com.example.demoMybatisPlus.menu.vo.MenuVo;
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
    public List<MenuVo> getMenus() {
        return this.getBaseMapper().getMenus();
    }

    @Override
    public List<MenuTreeVo> getMenusTree() {
        return this.getBaseMapper().getMenusTree();
    }
}
