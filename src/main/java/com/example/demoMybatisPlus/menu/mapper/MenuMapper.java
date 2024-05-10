package com.example.demoMybatisPlus.menu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demoMybatisPlus.menu.entity.Menu;
import com.example.demoMybatisPlus.menu.vo.MenuTreeVo;
import com.example.demoMybatisPlus.menu.vo.MenuVo;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author dingzk
 * @since 2024/05/06
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<MenuVo> getMenus();

    List<MenuTreeVo> getMenusTree();
}
