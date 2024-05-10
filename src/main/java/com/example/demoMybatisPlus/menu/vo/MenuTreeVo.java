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
public class MenuTreeVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("key")
    private String key;

    @ApiModelProperty("图标")
    private String nodeIcon;

    @ApiModelProperty("子菜单")
    private List<MenuTreeVo> children;

}
