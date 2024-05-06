package com.example.demoMybatisPlus.menu.controller;

import com.example.demoMybatisPlus.base.AjaxResult;
import com.example.demoMybatisPlus.menu.dto.MenuDto;
import com.example.demoMybatisPlus.menu.entity.Menu;
import com.example.demoMybatisPlus.menu.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author dingzk
 * @since 2024/05/06
 */
@Slf4j
@RestController
@RequestMapping("/menu")
@Api(tags = "MenuController", description = "菜单管理")
public class MenuController {
    private MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 集合查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "查询菜单集合", httpMethod = "POST")
    public AjaxResult<List<Menu>> list(
            @RequestBody MenuDto menuDto) {

        List<Menu> menus=menuService.getMenus();

        return AjaxResult.ok(menus);
    }

}
