package com.example.demoMybatisPlus.menu.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author dingzk
 * @since 2024/05/06
 */
@Slf4j
@Data
@Getter
@Setter
@ApiModel(value = "Menu对象", description = "菜单表")
public class Menu implements Serializable {

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

}
