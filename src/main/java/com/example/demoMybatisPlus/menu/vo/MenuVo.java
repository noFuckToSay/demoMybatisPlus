package com.example.demoMybatisPlus.menu.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * hello,world
 * </p>
 *
 * @author dingzk
 * @since 2024/05/06
 */
@Slf4j
@Data
public class MenuVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("父级菜单主键")
    private Long parentId;

    @ApiModelProperty("1.subMenu 2.menuItem")
    private Integer type;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("key")
    private String key;

    @ApiModelProperty("parentKey")
    private String parentKey;

    @ApiModelProperty("图标	")
    private String icon;

    @ApiModelProperty("vue-router路由地址")
    private String routeTo;

    @ApiModelProperty("菜单是否开启keep-alive缓存")
    private Boolean keepAlive;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("创建人")
    private String createBy;

    @ApiModelProperty("修改人")
    private String updateBy;

    @ApiModelProperty("逻辑删除")
    private Integer isDel;

    @ApiModelProperty("子菜单")
    private List<MenuVo> subMenus;

}
